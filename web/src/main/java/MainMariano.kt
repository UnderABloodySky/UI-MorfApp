package api

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import org.eclipse.jetty.http.HttpStatus.*
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import productAndMenu.Category

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

    // Instancio el controller (OJO, ESTE ES OTRO!!)
    // Le agrego data para poder probar inicialmente
    val menuController = MenuControllerContext()
    menuController.addProduct(Product(1, "a", "b", 10.0, Category.BEBIDA))
    menuController.addProduct(Product(2, "b", "d", 14.0, Category.ADICIONAL))
    menuController.addProduct(Product(3, "c", "f", 18.0, Category.POSTRE))

    // CRUD de Lugares
    // Sintaxis alternativa, mucho más concisa
    // Donde el comportamiento se traslada al controller
    app.routes {
        path("products") {
            get(menuController::getAll)
            post(menuController::addProduct)
            path(":code") {
                get(menuController::getProduct)
                put(menuController::updateProduct)
                delete(menuController::deleteProduct)
            }
        }
    }
}