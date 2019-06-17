package api


import applicationModel.MorfApp
import io.javalin.Context
import productAndMenu.Menu
import restaurant.Restaurant
import searcher.CriteriaById
import searcher.CriteriaByString

class RestaurantController {
  private var restaurants = mutableListOf<DataRestaurant>()

    fun addDataRestaurant(resto : Restaurant){
        var aDataResto = DataRestaurant(resto.code, resto.name, resto.direcction, resto.geoLocation)
        restaurants.add(aDataResto)
    }

    fun getAllRestaurants(ctx : Context){
        ctx.status(200)
        ctx.json(restaurants)
    }

    fun getAllMenus(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        ctx.json((MorfApp
                .findRestaurant(CriteriaById(code))
                .first() as Restaurant)
                .menus()
                .values)
    }

    fun getRestaurantsAndMenusByCriteria(ctx: Context) {
        val criteria = ctx.queryParam("q")
        val lat = ctx.queryParam("lat")
        val long = ctx.queryParam("long")

        var tempRestaurants = MorfApp.findRestaurant(CriteriaByString(criteria!!)) as MutableList<Restaurant>
        if (isNotNull(long)) tempRestaurants = tempRestaurants.filter {
            it.geoLocation.longitude == long!!.toDouble() } as MutableList<Restaurant>
        if (isNotNull(lat)) tempRestaurants = tempRestaurants.filter {
            it.geoLocation.latitude == lat!!.toDouble() } as MutableList<Restaurant>

        var tempMenus = mutableListOf<Menu>()
        MorfApp.restaurants.forEach{ tempMenus.addAll(it.value.findMenu(CriteriaByString(criteria)) as Collection<Menu>) }

        ctx.json(tempRestaurants + tempMenus)
    }

    fun isNotNull(nullable: Any?): Boolean {
        return nullable != null
    }
}
