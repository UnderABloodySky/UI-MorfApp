package api

import discount.Discount
import geoclaseui.Geo
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus
import paymentMethod.PaymentMethod

data class PaymentMethod(var id: Int, var name: String)
data class Restaurant(val code : Int,
                      var name : String,
                      var description : String,
                      var direction : String,
                      var geoLocation : Geo,
                      var availablePaymentMethods : MutableCollection<PaymentMethod>)

data class Menu(val code: Int,
                var name: String,
                var description: String,
                var productsOfMenu: MutableList<productAndMenu.Product>,
                var restaurant : Restaurant,
                var discount: Discount,
                var enabled: Boolean = true)

class RestaurantController{
    private val menus = mutableListOf<Menu>()
    private val restaurants = mutableListOf<Restaurant>()

    /** CRUD **/

    fun getAllMenus(ctx: Context) {
        ctx.json(this.menus)
    }

    fun getAllRestaurants(ctx: Context) {
        ctx.json(this.restaurants)
    }

    fun getMenu(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        ctx.json(getMenuById(code))
    }

    fun getRestaurant(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        ctx.json(getRestaurantById(code))
    }

    fun addRestaurant(ctx: Context) {
        val restaurant = ctx.body<Restaurant>()
        ctx.status(HttpStatus.CREATED_201)
        ctx.json(addRestaurant(restaurant))
    }

    fun addMenu(ctx: Context) {
        val menu = ctx.body<Menu>()
        ctx.status(HttpStatus.CREATED_201)
        ctx.json(addMenu(menu))
    }

    fun updateRestaurants(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        val viewRestaurant = ctx.body<Restaurant>()
        val oldRestaurant = getRestaurantById(code)
        val newRestaurant = Restaurant( oldRestaurant.code,
                                        viewRestaurant.name,
                                        viewRestaurant.description,
                                        viewRestaurant.direction,
                                        viewRestaurant.geoLocation,
                                        oldRestaurant.availablePaymentMethods)

        restaurants.remove(oldRestaurant)
        restaurants.add(newRestaurant)
        ctx.json(newRestaurant)
    }

    fun updateMenu(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        val viewMenu = ctx.body<Menu>()
        val oldMenu = getMenuById(code)
        val newMenu = Menu(oldMenu.code,
                viewMenu.name,
                viewMenu.description,
                viewMenu.productsOfMenu,
                oldMenu.restaurant,
                viewMenu.discount,
                viewMenu.enabled)

        menus.remove(oldMenu)
        menus.add(newMenu)
        ctx.json(newMenu)
    }

    fun deleteRestaurant(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        restaurants.remove(getRestaurantById(code))
        ctx.status(HttpStatus.NO_CONTENT_204)
    }

    fun deleteMenu(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        menus.remove(getMenuById(code))
        ctx.status(HttpStatus.NO_CONTENT_204)
    }

    /** Support Functions **/

    private fun getRestaurantById(code: Int): Restaurant {
        return restaurants.firstOrNull { it.code == code }
                ?: throw NotFoundResponse("No se encontró el restaurant con id $code")
    }

    private fun getMenuById(code: Int): Menu {
        return menus.firstOrNull { it.code == code }
                ?: throw NotFoundResponse("No se encontró el menu con id $code")
    }

    fun addMenu(menu: Menu): Menu{
        val newMenu = Menu( menu.code,
                            menu.name,
                            menu.description,
                            menu.productsOfMenu,
                            menu.restaurant,
                            menu.discount,
                            menu.enabled)
        menus.add(newMenu)
        return newMenu
    }

    fun addRestaurant(restaurant: Restaurant): Restaurant{
        val newRestaurant = Restaurant( restaurant.code,
                restaurant.name,
                restaurant.description,
                restaurant.direction,
                restaurant.geoLocation,
                restaurant.availablePaymentMethods)
        restaurants.add(newRestaurant)
        return newRestaurant
    }
}
