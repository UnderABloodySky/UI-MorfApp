package api


import applicationModel.MorfApp
import controllers.DataUser
import io.javalin.Context
import io.javalin.NotFoundResponse
import order.Order
import org.eclipse.jetty.http.HttpStatus
import paymentMethod.PaymentMethod
import productAndMenu.Menu
import restaurant.Restaurant
import scala.Tuple2
import searcher.CriteriaById
import user.Client
import user.User

data class Geo(var lat:Double,var long:Double)


data class OrderData(var code:Int,var restaurantId:Int,var clientID:String,var menusAccumulated: MutableMap<Int,Int>,var paymentMethod: PaymentMethod,var geoLocation:Geo  ){
   /* var clientiD = o
    var code = order.code
    var restaurantCode = order.getRestaurant().code
    //var menusAccumulated = order.getMenusAndCuantity()
    var menus = order.menus()
    var paymentMethod =order.getPaymentMethod()
*/
}


class OrderController() {
    private var orders = mutableListOf<OrderData>()
    private val morfApp = MorfApp;

    fun allOrders(ctx: Context) {
        print(orders)
        ctx.json(this.orders)
    }

    //la idea es que reciba lo que le viene por el json y devuelva la lista piola de los menus
    fun transformToMenuList(idsAccumulate:MutableMap<Int,Int>):MutableList<Menu>{

                var newMenus = mutableListOf<Menu>()
                idsAccumulate.forEach { m-> newMenus.add() }

    }



//hacer que esta mierda tome un data y cree un order .
    fun addOrder(ctx: Context) {
        val order = ctx.body<OrderData>()
        ctx.status(HttpStatus.CREATED_201)
        val client = morfApp.findClient(order.clientID)
        var menus = this.transformToMenuList(order.menusAccumulated)
        var restaurant = morfApp.findRestaurant(CriteriaById(order.restaurantId))as Restaurant

        var newOrder =client?.makeNewOrder(restaurant,menus,order.paymentMethod)
        ctx.json(orders.add(order))
    }

    fun getOrder(ctx: Context) {
        val code = ctx.pathParam("code").toInt()
        ctx.json(getOrderById(code))
    }
    //funciones complementarias

    fun getOrderById(code: Int): OrderData {
        print(orders)
        var orderCorrect = orders.find { it.code == code }
        return orderCorrect
                ?: throw NotFoundResponse("No se encontró la orden con id $code")
    }

   /* fun addOrderComplentary(modelOrder: order.Order): Order {
        // client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu>

        val newOrder = morfApp.createOrder(modelOrder.getUser(),
                modelOrder.getRestaurant(),
                modelOrder.getPaymentMethod(),
                modelOrder.getMenu())
        this.addOrderData(newOrder)
        return newOrder

    }*/



}








