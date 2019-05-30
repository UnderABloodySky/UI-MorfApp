package api

import applicationModel.MorfApp
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpStatus.*
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import discount.Discount
import discount.NoDiscount
import order.Order
import geoclase.Geo
import paymentMethod.Cash
import paymentMethod.Debit
import paymentMethod.PaymentMethod
import productAndMenu.Category
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant
import user.Client
import java.util.*




fun main() {
    val app = Javalin.create()
            .enableRouteOverview("/routes")
            .exception(MismatchedInputException::class.java) { e, ctx ->
                ctx.status(BAD_REQUEST_400)
                ctx.json(mapOf(
                        "status" to BAD_REQUEST_400,
                        "message" to e.message
                ))
            }
            .start(8000)
    app.get("/") { ctx -> ctx.json(mapOf("message" to " Welcome to MorfApp ~ Online ")) }

    // Instancio el controller (OJO, ESTE ES OTRO!!)
    // Le agrego data para poder probar inicialmente

    var morfApp = MorfApp
   /*var paymentMethods = mutableListOf<PaymentMethod>()

    var restaurant1 = morfApp.createRestaurant ( "La conga", "buena onda", "Roque saenz pena",
            localization, paymentMethods)

    var menusProducts = mutableListOf<productAndMenu.Product>()
    menusProducts.add(productAndMenu.Product(2, "Coca", "Regular", 34.0, Category.BEBIDA))
    menusProducts.add(productAndMenu.Product(3, "Pollo", "A la plancha", 80.0, Category.PLATOPRINCIPAL))


    var menu2 =
            productAndMenu.Menu(1, "Ejecutivo", "solo al medio día", menusProducts,
                    restaurant1, NoDiscount(), true)
    var menuList = mutableListOf<Menu>()
    menuList.add(menu2)

    var clienteDeMierda = Client(66,"juancho","otroId","mi casa",localization,"1234","aaa@qq.com")
    //var clientModel = MorfApp.createClient("1","marina","111",localization,"aaa")

    var restaurantData = Restaurant(5, "lo de moe", "mmm", "cerca de aca ", localization, paymentMethods)
    var listOfDataMenus = mutableListOf<Menu>()

    listOfDataMenus.add(Menu(4, "papa", "fritas", mutableListOf<Product>(), restaurantData, NoDiscount(), true))

    var localizationData = Geo(2.00, 3.00)

    var debitCard = Debit()


    var order2 = order.Order(6,clienteDeMierda,restaurant1,debitCard,menuList)
    //  var order1= Order(1,restaurant1.code,debitCard,localizationData,listOfDataMenus)



*/
    var localization = geoclase.Geo(2.00, 2.00,"algun lado")
    val elTano = morfApp.createRestaurant("elTano", "Llenadero magico de tripas", "Por Avellaneda", localization, mutableListOf(Cash()))


    val parrilada2 = elTano.createProduct("Parrillada para 2", "", 100.0, Category.POSTRE)
    val menu0 = elTano.createMenu("Menu1", "", mutableListOf(parrilada2), elTano, discount.NoDiscount(), true)

    val mChaile = morfApp.createClient("NinjaMan", "Matias Chaile", "Roque Saenz Peña 500", localization, "dilequechupelimon", "mailTrucho0@asd.com")
    val orderP = mChaile.makeNewOrder(elTano, mutableListOf(),PaymentMethod().cash())

    orderP.addMenu(menu0)


    val orderController = OrderController()

    orderController.addOrderComplentary(orderP)


    // CRUD de Lugares
    // Sintaxis alternativa, mucho más concisa
    // Donde el comportamiento se traslada al controller
    app.routes {
        path("orders"){
            get(orderController::allOrders)
            post(orderController::addOrder)
            path(":code"){
                get(orderController::getOrder)
            }
        }
    }
}