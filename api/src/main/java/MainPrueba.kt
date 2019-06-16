package api

import applicationModel.MorfApp
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import geoclase.Geo
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.core.util.Header
import org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400
import paymentMethod.*
import productAndMenu.Category
import productAndMenu.Product
import java.util.*

fun main() {
    val app = Javalin.create()
            .enableCorsForAllOrigins()
            .enableRouteOverview("/routes")
            .exception(MismatchedInputException::class.java) { e, ctx ->
                ctx.status(BAD_REQUEST_400)
                ctx.json(mapOf(
                        "status" to BAD_REQUEST_400,
                        "message" to e.message
                ))
            }
            .start(7000)
    app.get("/") { ctx -> ctx.json(mapOf("message" to " Welcome to MorfApp ~ Online ")) }


    val morfApp = MorfApp
    //Ubicacion usuarios
    val unq = Geo(-34.706272, -58.278519, "UNQ")
    val bernal = Geo(-34.709390, -58.280507, "Bernal")
    val capital = Geo(-34.603595, -58.381717, "Obelisco")
    val mordor = Geo(-666.0, -666.0, "Remedios de Escalada")

    //Ubicacion restaurants
    val flores = Geo(-34.603595, -58.381717, "Flores")

    val mChaile = morfApp.createClient("NinjaMan", "Matias Chaile", "Roque Saenz Peña 500", unq, "dilequechupelimon", "mailTrucho0@asd.com")
    val mPais = morfApp.createClient("BBQMaster", "Mariano Pais", "Siempre Viva 442", bernal, "proyecto", "mailTrucho1@asd.com")
    val jLajcha = morfApp.createClient("RunForestRun", "Juliana Lajcha", "Calle Falsa 1234", capital, "1234", "mailTrucho2@asd.com")
    val fCaramelieri = morfApp.createClient("OracleFanBoy", "Fede Caramelieri", "Otra Calle Falsa 4321", bernal, "plusvalia", "mailTrucho3@asd.com")

    val cash = Cash()
    val debit = Debit("pepe", 12121212, 123, Date())
    val creditCard = CreditCard("pepe", 1212121212, 123, Date())
    val mercadoPago = MercadoPago("pepe", "1233")

    val onlyCash = mutableListOf<PaymentMethod>(cash)
    val cashDebitAndCreditCard = mutableListOf(cash, debit, creditCard)
    val everything = mutableListOf(cash, debit, creditCard, mercadoPago)

    val laConga = morfApp.createRestaurant("laConga", "Un antro", "Rivadavia al algo", flores, onlyCash)
    val guerrin = morfApp.createRestaurant("guerrin", "Alta pizza", "Por Corrientes", capital, everything)
    val elTano = morfApp.createRestaurant("elTano", "Llenadero magico de tripas", "Por Avellaneda", mordor, cashDebitAndCreditCard)

    val helado = laConga.createProduct("Helado", "Hecho con leche de vacas contentas", 100.0, Category.POSTRE)
    val cocaCola = laConga.createProduct("Coca Cola", "Azucar al 200%", 80.0, Category.BEBIDA)
    val hamburguesa = laConga.createProduct("Hamburguesa", "Aprobadas por la Universidad Bovina", 120.0, Category.PLATOPRINCIPAL)
    val productsOfLaConga = mutableListOf<Product>(cocaCola, hamburguesa)

    val muzza = guerrin.createProduct("Pizza Muzzarella", "Hecho con leche de vacas contentas", 120.0, Category.PLATOPRINCIPAL)
    val cuatroQuesos = guerrin.createProduct("Pizza 4 quesos", "Azucar al 200%", 280.0, Category.PLATOPRINCIPAL)
    val faina = guerrin.createProduct("faina", "Garbanzo power", 120.0, Category.ADICIONAL)
    val pepsi = guerrin.createProduct("Pepsi", "Azucar al 190%", 80.0, Category.BEBIDA)
    val productsOfGuerrin = mutableListOf(muzza, cuatroQuesos, faina, pepsi)
    val productsGuerrin = mutableListOf(muzza, pepsi)
    val vacio = elTano.createProduct("vacio", "", 80.0, Category.PLATOPRINCIPAL)
    val parrilada2 = elTano.createProduct("Parrillada para 2", "", 100.0, Category.POSTRE)
    val parrilada1 = elTano.createProduct("Parrillada para 1", "", 120.0, Category.ADICIONAL)
    val chori = elTano.createProduct("choripan", "", 80.0, Category.BEBIDA)
    val productsOfElTano = mutableListOf(vacio, parrilada1, parrilada2, chori)
    val productsTano = mutableListOf(vacio, chori)

    val menu0 = laConga.createMenu("Menu1", "Bien barato", mutableListOf(helado), laConga, discount.NoDiscount(), true)
    val menu1 = laConga.createMenu("Menu2", "carito", productsOfLaConga, laConga, discount.NoDiscount(), true)
    val menu3 = guerrin.createMenu("MenuB", "chetito", productsGuerrin, guerrin, discount.FixedDiscount(5.0), true)

    val orderP = mChaile.makeNewOrder(elTano, mutableListOf(), cash)
    orderP.addMenu(menu0)
    orderP.processOrder()

    val orderH0 = mChaile.makeNewOrder(guerrin, mutableListOf(), cash)
    orderH0.addMenu(menu3)
    orderH0.processOrder()
    orderH0.delivered()

    val orderH1 = mChaile.makeNewOrder(elTano, mutableListOf(), cash)
    orderH1.addMenu(menu1)
    orderH1.processOrder()
    orderH1.delivered()

    val controller = SuperController()
    controller.addDataUser(mChaile)
    controller.addDataUser(mPais)
    controller.addDataUser(jLajcha)
    controller.addDataUser(fCaramelieri)

    app.routes {
        path("login") {
            post(controller::login)
        }
        path("users") {
            get(controller::getAllUsers)
            path(":id") {
                get(controller::findUser)
                put(controller::updateUser)
                delete(controller::deleteUser)
            }
            path("email") {
                path(":email") {
                    get(controller::findUserByMail)
                }
            }
            path("register_form") {
                post(controller::addUserByForm)
            }
            path("register") {
                post(controller::addUser)
            }
        }

        path("restaurant") {
            path(":code") {
                get(controller::getAllMenus)
            }
        }
        path("search") {
            get(controller::getRestaurantsAndMenusByCriteria)
        }

        path("orders_historic") {
            path(":id") {
                get(controller::historicOrders)
                path(":code_order") {
                    put(controller::rateAnOrder)
                }
            }
        }


        path("orders_pending") {
            path(":id") {
                get(controller::pendingOrders)
                post(controller::addOrder)
                path(":code_order") {

                }
            }
        }
    }
}




