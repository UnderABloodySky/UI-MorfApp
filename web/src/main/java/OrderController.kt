package api


import applicationModel.MorfApp
import controllers.DataUser
import io.javalin.Context
import io.javalin.NotFoundResponse
import order.Order
import org.eclipse.jetty.http.HttpStatus
import productAndMenu.Menu
import restaurant.Restaurant
//import scala.Tuple2
import user.Client
import user.User

data class Geo(var lat:Double,var long:Double)


data class OrderData(var order:Order ){

    var code = order.code
    var client =order.getUser()
    var restaurantCode = order.getRestaurant().code
    var paymentMethod =order.getPaymentMethod()
    var menus = order.menus()
  //  var menusAccumulated = order.getMenusAndCuantity()
}


class OrderController() {
    private var orders = mutableListOf<OrderData>()
    private val morfApp = MorfApp;

    fun allOrders(ctx: Context) {
        print(orders)
        ctx.json(this.orders)
    }

    fun addOrder(ctx: Context) {
        val order = ctx.body<Order>()
        ctx.status(HttpStatus.CREATED_201)

        var newOrder = morfApp.createOrder(order.getUser(),
                order.getRestaurant(),
                order.getPaymentMethod(),
                order.getMenu())
        ctx.json(addOrderData(newOrder))
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

    fun addOrderComplentary(modelOrder: order.Order): Order {
        // client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu>

        val newOrder = morfApp.createOrder(modelOrder.getUser(),
                modelOrder.getRestaurant(),
                modelOrder.getPaymentMethod(),
                modelOrder.getMenu())
        this.addOrderData(newOrder)
        return newOrder

    }

    fun addOrderData(order: Order): OrderData {
        var newDataOrder = OrderData(order)
        orders.add(newDataOrder)
        return newDataOrder
    }



}








