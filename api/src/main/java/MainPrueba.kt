package api

import applicationModel.MorfApp
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import discount.NoDiscount
import geoclase.Geo
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
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
    var geoLocation1 = Geo(1.2, 2.2)
    var geoLocation0 = Geo(1.2, 2.2)


    //Ubicacion restaurants
    val flores = Geo(-34.603595, -58.381717, "Flores")

    val mChaile = morfApp.createClient("NinjaMan", "Matias Chaile", "Roque Saenz Peña 500", unq, "dilequechupelimon", "mailTrucho0@asd.com")
    val mPais = morfApp.createClient("BBQMaster", "Mariano Pais", "Siempre Viva 442", bernal, "proyecto", "mailTrucho1@asd.com")
    val jLajcha = morfApp.createClient("RunForestRun", "Juliana Lajcha", "Calle Falsa 1234", capital, "1234", "mailTrucho2@asd.com")
    val fCaramelieri = morfApp.createClient("OracleFanBoy", "Fede Caramelieri", "Otra Calle Falsa 4321", bernal, "plusvalia", "mailTrucho3@asd.com")

    val cash = Cash()
    val debit = Debit("pepe", "12121212", "123", "12/34")
    val creditCard = CreditCard("pepe", "12121212", "123", "12/34")
    val mercadoPago = MercadoPago("pepe", "1233")

    val onlyCash = mutableListOf<PaymentMethod>(cash)
    val cashDebitAndCreditCard = mutableListOf(cash, debit, creditCard)
    val everything = mutableListOf(cash, debit, creditCard, mercadoPago)

    val laConga = morfApp.createRestaurant("laConga", "Un antro", "Rivadavia al algo", flores, everything)
    val guerrin = morfApp.createRestaurant("guerrin", "Alta pizza", "Por Corrientes", capital, everything)
    val elTano = morfApp.createRestaurant("elTano", "Llenadero magico de tripas", "Por Avellaneda", mordor, cashDebitAndCreditCard)
    var newRestaurant = morfApp.createRestaurant("Asd", "asd", "asd", geoLocation1, mutableListOf())
    var elClubDeLaMilanesa = morfApp.createRestaurant("El club de la milanesa", "asd", "asd", geoLocation1, mutableListOf())
    var otroAntro = morfApp.createRestaurant("Asd", "las mejores milanesas", "asd", geoLocation1, mutableListOf())

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
    val parrillada2 = elTano.createProduct("Parrillada para 2", "", 100.0, Category.POSTRE)
    val parrillada1 = elTano.createProduct("Parrillada para 1", "", 120.0, Category.ADICIONAL)
    val chori = elTano.createProduct("choripan", "", 80.0, Category.BEBIDA)
    val productsOfElTano = mutableListOf(vacio, parrillada1, parrillada2, chori)
    val productsTano = mutableListOf(vacio, chori)

    val menu0 = laConga.createMenu("Menu1", "baratito", mutableListOf(helado), laConga, discount.NoDiscount(), true)
    val menu1 = laConga.createMenu("Menu2", "carito", productsOfLaConga, laConga, discount.NoDiscount(), true)
    val menu2 = laConga.createMenu("Menu3", "chetito", productsOfLaConga, laConga, discount.FixedDiscount(5.0), true)
    val menu4 = laConga.createMenu("Menu4", "riquito", productsOfLaConga, laConga, discount.PercentageDiscount(20.0), true)
    val menu3 = guerrin.createMenu("MenuB", "chetito", productsGuerrin, guerrin, discount.FixedDiscount(5.0), true)
    val menu5 = elTano.createMenu("Menu4", "riquito", productsOfElTano, elTano, discount.PercentageDiscount(20.0), true)

    var one = newRestaurant.createMenu("Con Milanesa", "asd", mutableListOf(), newRestaurant, NoDiscount(), true)
    var two = newRestaurant.createMenu("asd2", "milanesa", mutableListOf(), newRestaurant, NoDiscount(), true)
    var three = elClubDeLaMilanesa.createMenu("Con Milanesa", "asd", mutableListOf(), elClubDeLaMilanesa, NoDiscount(), true)
    var four = elClubDeLaMilanesa.createMenu("asd", "con milanesa", mutableListOf(), elClubDeLaMilanesa, NoDiscount(), true)
    var five = otroAntro.createMenu("asd3", "asd", mutableListOf(), otroAntro, NoDiscount(), true)
    var six = otroAntro.createMenu("asd4", "asd", mutableListOf(), otroAntro, NoDiscount(), true)


    menu0.addImageToMenu("http://lasrecetascaseras.com/wp-content/uploads/2016/09/avena-para-adelgazar-100x100.jpg")
    menu1.addImageToMenu("https://hips.hearstapps.com/dm.h-cdn.co/assets/16/22/980x980/square-1465096043-un-plato-para-cada-dia-de-la-semana.jpg?resize=100:*")
    menu2.addImageToMenu("http://www.annarecetasfaciles.com/files/pollo-en-salsa-1-100x100.jpg")
    menu3.addImageToMenu("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AABK1dg.img?h=100&w=100&m=6&q=60&u=t&o=t&l=f&x=310&y=568")
    menu4.addImageToMenu("https://s3-media3.fl.yelpcdn.com/bphoto/Uer9WLb4Bk0oMQm9r9tmSA/ms.jpg")
    menu5.addImageToMenu("http://www.annarecetasfaciles.com/files/pollo-en-salsa-1-100x100.jpg")

    val orderP = mChaile.makeNewOrder(laConga, mutableListOf(), cash)
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

    controller.addDataRestaurant(laConga)
    controller.addDataRestaurant(guerrin)
    controller.addDataRestaurant(elTano)

    controller.addDataMenu(menu0)
    controller.addDataMenu(menu1)
    controller.addDataMenu(menu2)
    controller.addDataMenu(menu3)
    controller.addDataMenu(menu4)	
    controller.addDataMenu(menu5)

    controller.addModelProduct(helado)
    controller.addModelProduct(cocaCola)
    controller.addModelProduct(hamburguesa)
    controller.addModelProduct(muzza)
    controller.addModelProduct(faina)
    controller.addModelProduct(cuatroQuesos)
    controller.addModelProduct(pepsi)
    controller.addModelProduct(vacio)
    controller.addModelProduct(parrillada2)
    controller.addModelProduct(parrillada1)
    controller.addModelProduct(chori)

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

        path("findrestaurant") {
            path(":code") {
                get(controller::getRestaurant)
            }
        }

        path("menus"){
            get(controller::getMenus)
        }

        path("products"){
            get(controller::getAllProducts)
        }

        path("restaurant") {
            get(controller::getAllRestaurants)
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

        path("deliver"){
            post(controller::addOrder)
        }

        path("orders_pending") {
            path(":id") {
                get(controller::pendingOrders)
                path(":code_order") {

                }
            }
        }
    }
}




