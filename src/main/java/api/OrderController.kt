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
data class MenusAndAmount(var menuId:Int,var ammount:Int)

data class OrderData(var code:Int,var restaurant:Int,var menus: MutableList<MenusAndAmount>,var clientID:String,var paymentMethod: PaymentMethod){
  }

class OrderController() {
    private var orders = mutableListOf<OrderData>()
    private val morfApp = MorfApp;

    fun allOrders(ctx: Context) {
        print(orders)
        ctx.json(this.orders)
    }

    //la idea es que reciba lo que le viene por el json y devuelva la lista piola de los menus
    fun transformToMenuList(idsAccumulate:MutableList<MenusAndAmount>,resto:Restaurant):MutableList<Menu>{
                var allMenus = resto.menus()
                var newMenus = mutableListOf<Menu>()
                idsAccumulate.forEach { m-> var menuToAdd = allMenus.get(m.menuId)!!
                                            newMenus.add(menuToAdd)

                                        }
                return newMenus
    }



//hacer que esta mierda tome un data y cree un order .
    fun addOrder(ctx: Context) {
        val order = ctx.body<OrderData>()

        val client = morfApp.findClient(order.clientID)!!

        var restaurant = morfApp.findRestaurant(CriteriaById(order.restaurant))as Restaurant
        var menus = this.transformToMenuList(order.menus,restaurant)
        client.makeNewOrder(restaurant,menus,order.paymentMethod)

        ctx.status(HttpStatus.CREATED_201)
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

    fun addOrderComplentary(modelOrder: order.Order): Order {
        // client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu>

        /*val newOrder = morfApp.createOrder(modelOrder.getUser(),
                modelOrder.getRestaurant(),
                modelOrder.getPaymentMethod(),
                modelOrder.getMenu())*/
        this.addOrderData(modelOrder)
        return modelOrder

    }

    fun transforToMenuAndAmount (map:MutableMap<Int,Int>):MutableList<MenusAndAmount>{
        var list= mutableListOf<MenusAndAmount>()
        map.forEach { m-> var mamount= MenusAndAmount(m.key,m.value)
                            list.add(mamount)}
        return list
    }

    fun addOrderData(order:Order){
       var  orderData = OrderData(order.code,order.getRestaurant().code,this.transforToMenuAndAmount(order.getMenusAndCuantity()),order.getUser().id,order.getPaymentMethod())
       orders.add(orderData)

    }


}
