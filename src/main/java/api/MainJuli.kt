package api

import applicationModel.MorfApp
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpStatus.*
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import discount.Discount
import discount.NoDiscount
import geoclaseui.Geo
import order.Order
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

    app.get("/") { ctx -> ctx.json(mapOf("message" to "Hello World")) }

    // Instancio el controller (OJO, ESTE ES OTRO!!)
    // Le agrego data para poder probar inicialmente


    var paymentMethods = mutableListOf<api.PaymentMethod>()
    var localization = Geo(2.00, 2.00)
    var restaurant1 =
            Restaurant(1, "La conga", "buena onda", "Roque saenz pena",
                    localization, paymentMethods)

    var menusProducts = mutableListOf<productAndMenu.Product>()
    menusProducts.add(productAndMenu.Product(2, "Coca", "Regular", 34.0, Category.BEBIDA))
    menusProducts.add(productAndMenu.Product(3, "Pollo", "A la plancha", 80.0, Category.PLATOPRINCIPAL))


    var menu2 =
            productAndMenu.Menu(1, "Ejecutivo", "solo al medio día", menusProducts,
                    restaurant1, NoDiscount(), true)
    var menuList = mutableListOf<Menu>()
    menuList.add(menu2)

    var clienteDeMierda = Client(66,"juancho","otroId","mi casa",Date(10-2-2019),localization,"123",MorfApp)
    var clientModel = MorfApp.createClient("1","marina","111",localization,"aaa")

    var restaurantData = api.Restaurant(5, "lo de moe", "mmm", "cerca de aca ", localization, paymentMethods)
    var listOfDataMenus = mutableListOf<api.Menu>()

    listOfDataMenus.add(api.Menu(4, "papa", "fritas", mutableListOf<Product>(), restaurantData, NoDiscount(), true))

    var localizationData = Geo(2.00, 3.00, "Bernal")

    var debitCard = Debit()


    var order2 = order.Order(6,clienteDeMierda,restaurant1,debitCard,menuList)
    //  var order1= Order(1,restaurant1.code,debitCard,localizationData,listOfDataMenus)

    val orderController = OrderController()
    orderController.addMenu(menu2)
    orderController.addOrderComplentary(order2)


    // CRUD de Lugares
    // Sintaxis alternativa, mucho más concisa
    // Donde el comportamiento se traslada al controller
    app.routes {
        path("menus") {
            get(orderController::allMenus)
            // post(orderController::addOrder)
            // path(":code") {
            //              get(menuController::getProduct)
            //            put(menuController::updateProduct)
            //          delete(menuController::deleteProduct)
        }
        path("orders"){
            get(orderController::allOrders)
            post(orderController::addOrder)
            path(":code"){
                get(orderController::getOrder)
            }
        }
    }
}
