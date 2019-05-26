package api


import applicationModel.MorfApp
import geoclaseui.Geo
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus
import restaurant.Restaurant
import user.Client

data class Geo(var lat:Double,var long:Double)

data class OrderData(var code:Int,var client:User, var restaurantCode: Int, var paymentMethod:paymentMethod.PaymentMethod,var menus : MutableCollection<productAndMenu.Menu>)


class OrderController() {
    private var orders = mutableListOf<OrderData>()
    private var menus = mutableListOf<Menu>()
    private var lastId = 0;


    fun allMenus(ctx: Context) {
        ctx.json(this.menus)
    }

    fun addOrder(ctx: Context) {

        val order = ctx.body<order.Order>()
        ctx.status(HttpStatus.CREATED_201)
        ctx.json(addOrderComplentary(order))

    }

    fun allOrders(ctx: Context){
        print(orders)
        ctx.json(this.orders)

    }

    fun addCalification(ctx: Context){

        val code = ctx.pathParam("code").toInt()
        ctx.json(getOrderById(code))
    }

    fun getOrder(ctx:Context){
        val code = ctx.pathParam("code").toInt()
        ctx.json(getOrderById(code))
    }
    //funciones complementarias

    fun getOrderById(code:Int):OrderData{
        print(orders)
        var orderCorrect = orders.find { it.code == code }
        return orderCorrect
                ?: throw NotFoundResponse("No se encontró la orden con id $code")
    }


    fun menuToData(menu: productAndMenu.Menu):Menu{
        var menuData = Menu(menu.code,menu.name,menu.description,menu.productsOfMenu,this.transformToRestaurantData(menu.restaurant),menu.discount,menu.enabled)
        return menuData
    }

    fun listOfDataMenus(listOfMenus:MutableList<Menu>):MutableList<Menu>{
        listOfMenus.forEach { menu-> var mData = this.menuToData(menu)
            menus.add(mData)
        }
        return menus
    }

    fun addOrderComplentary(modelOrder: order.Order):OrderData {
        // client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu>

        val newOrder = OrderData(modelOrder.code,
                User(modelOrder.getUser().code),
                modelOrder.getRestaurant().code,
                modelOrder.getPaymentMethod(),
                modelOrder.menus())
        orders.add(newOrder)
        return newOrder
    }


    fun transformToRestaurantData(retaurant: Restaurant):api.Restaurant{
        var dataRestaurant=   api.Restaurant(retaurant.code,
                retaurant.name,
                retaurant.description,
                retaurant.direcction,
                retaurant.geoLocation,
                retaurant.availablePaymentMethods)
        return dataRestaurant
    }


    fun addMenu(aMenu:productAndMenu.Menu):Menu{

        val menuNew = Menu(++this.lastId,
                aMenu.name,
                aMenu.description,
                aMenu.productsOfMenu,
                this.transformToRestaurantData( aMenu.restaurant),
                aMenu.discount,
                aMenu.enabled)
        menus.add(menuNew)
        return menuNew
    }

}







