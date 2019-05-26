package api

import applicationModel.MorfApp
import discount.Discount
import geoclaseui.Geo
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus
import searcher.CriteriaById
import searcher.CriteriaByIdAndString
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class PaymentMethod(var id: Int, var name: String, var paymentM: paymentMethod.PaymentMethod)
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
        val criteria = ctx.queryParam("q")
        val lat = ctx.queryParam("lat") as Double?
        val long= ctx.queryParam("long") as Double?

        val tempRestaurants = this.findRestaurant(criteria, lat, long)
        val tempMenus = this.findMenu(criteria)
        ctx.json(tempRestaurants + tempMenus)

    }

    private fun findMenu(criteria: String?): MutableList<Menu> {
        var tempMenus = mutableListOf<Menu>()
        if (isNotNull(criteria)) tempMenus = filterMenuByCriteria(this.menus, criteria)
        return tempMenus
    }

    private fun filterMenuByCriteria(menus: MutableList<Menu>, criteria: String?): MutableList<Menu> {
        return menus.filter { it.name.contains(Regex(criteria!!)) } as MutableList<Menu>
    }

    private fun findRestaurant(criteria: String?, lat: Double?, long: Double?): MutableList<Restaurant> {
        var tempRestaurants = mutableListOf<Restaurant>()
        if (isNotNull(criteria)) tempRestaurants = filterRestaurantByCriteria(this.restaurants, criteria)
        if (isNotNull(lat)) tempRestaurants = filterByLatitude(tempRestaurants, lat)
        if (isNotNull(long)) tempRestaurants = filterByLongitude(tempRestaurants, long)
        return  tempRestaurants
    }

    private fun filterRestaurantByCriteria(restaurants: MutableList<Restaurant>, criteria: String?): MutableList<Restaurant> {
        return restaurants.filter { it.name.contains(Regex(criteria!!)) } as MutableList<Restaurant>
    }

    private fun filterByLongitude(restaurants: MutableList<Restaurant>, long: Double?): MutableList<Restaurant> {
        return restaurants.filter { it.geoLocation.longitude == long } as MutableList<Restaurant>
    }

    private fun filterByLatitude(restaurants: MutableList<Restaurant>, lat: Double?): MutableList<Restaurant> {
        return restaurants.filter { it.geoLocation.latitude == lat } as MutableList<Restaurant>
    }

    private fun isNotNull(nullable: Any?): Boolean{
        return nullable != null
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

    fun updateRestaurant(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        val viewRestaurant = ctx.body<Restaurant>()
        val oldRestaurant = getRestaurantById(code)
        val newRestaurant = Restaurant( viewRestaurant.code,
                                        viewRestaurant.name,
                                        viewRestaurant.description,
                                        viewRestaurant.direction,
                                        viewRestaurant.geoLocation,
                                        viewRestaurant.availablePaymentMethods)

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
        var tempRest = MorfApp.findRestaurant(CriteriaById(menu.restaurant.code)).first() as restaurant.Restaurant
        tempRest.createMenu(menu.name, menu.description, menu.productsOfMenu, tempRest, menu.discount, menu.enabled)

        var tempMenu = tempRest.findMenu(CriteriaById(menu.code)).first()
        val newMenu = Menu( tempMenu!!.code,
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
        MorfApp.createRestaurant(   restaurant.name,
                                    restaurant.description,
                                    restaurant.direction,
                                    restaurant.geoLocation,
                                    mutableListOf<paymentMethod.PaymentMethod>())
        restaurants.add(restaurant)

        return restaurant
    }
}
