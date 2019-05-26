package api

import applicationModel.MorfApp
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpStatus.*
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import discount.NoDiscount
import geoclaseui.Geo
import productAndMenu.Category
import productAndMenu.Product

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
            .start(7000)

    app.get("/") { ctx -> ctx.json(mapOf("message" to "Hello World")) }

    var unaHamburguesaSalvaje = Product(0,"Hamburguesa", "Al vapor", 100.00, Category.NINGUNO)

    val geo = Geo(123.0, 123.0, "123")
    val availablePM = mutableListOf<PaymentMethod>()

    val restaurantController = RestaurantController()

    val tempRest = Restaurant(1, "Pipox", "Peru", "falsa 123", geo, availablePM)

    restaurantController.addRestaurant(tempRest)

    restaurantController.menus.add(Menu(0, "pep", "a", mutableListOf(unaHamburguesaSalvaje), tempRest, NoDiscount(), true))

    restaurantController.addRestaurant(
            Restaurant(2, "Pepex", "Peru", "falsa 234", geo, availablePM))
    restaurantController.addRestaurant(
            Restaurant(3, "Milanga", "Peru", "falsa 345", geo, availablePM))

    // CRUD de Lugares
    // Sintaxis alternativa, mucho más concisa
    // Donde el comportamiento se traslada al controller

    app.routes {
        path("search"){
                get(restaurantController::getRestaurant)
        }

    }

}