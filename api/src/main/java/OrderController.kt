package api


import applicationModel.MorfApp
import io.javalin.Context
import io.javalin.NotFoundResponse
import order.DataMenuInOrder
import order.Order
import org.eclipse.jetty.http.HttpStatus
import paymentMethod.*
import productAndMenu.Menu
import restaurant.Restaurant
import java.util.*

data class RateData(var rating:Int)
data class Geo(var lat:Double,var long:Double)
data class MenusAndAmount(var menuId:Int,var ammount:Int)
data class PaymentMethodsParameters(var type:String, var user:String?,var password:String?,
                                    var cardNumber:Int?,var cardOwnerName:String?,var cardExpirationDate:Date?,var cardCode:Int?)

data class OrderData(var codeOrder:Int,var restaurant:Int,var menus: MutableList<MenusAndAmount>,
                     var clientID:String,var paymentMethod: PaymentMethodsParameters){
    var ratingData:RateData= RateData(0)
}

//creo este data para que devolverlo con el metodo de pago entero y no la villereada de arriba
data class OrderDataComplete(var code_order_complete:Int,var restaurant:Int,var restaurantName:String ,var menus: MutableList<DataMenuInOrder>,
                             var clientID:String,var paymentMethod: PaymentMethod){
    var ratingData:RateData= RateData(0)
}


class OrderController() {
    private var orders = mutableListOf<OrderData>()
    private val morfApp = MorfApp;


    //refactorizar esto. ver por que no está handleando el error
    fun transformOrdersToOrderData(order: MutableList<Order>):MutableList<OrderDataComplete>{
        var orderDatas = mutableListOf<OrderDataComplete>()
        order.forEach { o-> var orderDataNew = OrderDataComplete(o.code,o.getRestaurant().code,o.restaurantName,
                o.getMenusAndCuantity(),
                o.getUser().id,o.getPaymentMethod())
            orderDatas.add(orderDataNew)
        }
        return  orderDatas

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






    fun historicOrders(ctx: Context) {
        val userId = ctx.pathParam("code")
        var client =  try{morfApp.findClient(userId)}
                      catch (e: Exception)
                        {throw NotFoundResponse("No se encontró la orden con id $userId")}

        var ordersDataComplete= this.transformOrdersToOrderData(client!!.historicOrders)
        ctx.status(HttpStatus.OK_200)
        ctx.json(ordersDataComplete)
    }

    fun isNotNull(nullable: Any?): Boolean {
        return nullable != null
    }
    //hacer que esta mierda tome un data y cree un order .
    fun rateAnOrder(ctx: Context){
        val rate = ctx.body<RateData>()
        val codeUser = ctx.pathParam("id")
        val codeOrder = ctx.pathParam("code_order")!!.toInt()

        var client =  morfApp.findClient(codeUser)

        var orderToUpdate = client!!.findOrderInCollection(codeOrder)


        client.rateOrder(orderToUpdate,rate.rating)


        if (this.isNotNull(codeOrder)){
            var orderToUpdate = client!!.findOrderInCollection(codeOrder)
            var orderDataComplete = OrderDataComplete(orderToUpdate.code,orderToUpdate.getRestaurant().code,
                    orderToUpdate.getRestaurant().name,
                    orderToUpdate.getMenusAndCuantity(),orderToUpdate.getUser().id,
                    orderToUpdate.getPaymentMethod())
            orderDataComplete.ratingData = rate
            ctx.status(HttpStatus.CREATED_201)
            ctx.json(orderDataComplete)
        }
    }


    fun pendingOrders(ctx: Context) {
        val userId = ctx.pathParam("id")
        var client =  morfApp.findClient(userId)?:throw NotFoundResponse("No se encontró la orden con id $userId")
        var ordersDataComplete= this.transformOrdersToOrderData(client.pendingOrders)
        ctx.status(HttpStatus.OK_200)
        ctx.json(ordersDataComplete)
    }


    fun addOrder(ctx: Context) {
        val order = ctx.body<OrderData>()

        val client = morfApp.findClient(order.clientID)!!
        var paymentMethod = this.createPaymentMethodApropieted(order.paymentMethod)
        var restaurant:Restaurant? = morfApp.findOtherRestaurant(order.restaurant)
        var menus = this.transformToMenuList(order.menus,restaurant!!)
        client.makeNewOrder(restaurant,menus,paymentMethod)

        ctx.status(HttpStatus.CREATED_201)
    }




    //funciones complementarias

    fun createPaymentMethodApropieted(parametersMethods:PaymentMethodsParameters):PaymentMethod {
        val type = parametersMethods.type
        var newPaymentMethod:PaymentMethod =  paymentMethod.Cash()
        if(type == "CreditCard"){
            newPaymentMethod = CreditCard(parametersMethods.cardOwnerName!!,parametersMethods.cardNumber!!,parametersMethods.cardCode!!,parametersMethods.cardExpirationDate!!)
        }
        if (type == "DebitCard"){
            newPaymentMethod = Debit(parametersMethods.cardOwnerName!!,parametersMethods.cardNumber!!,parametersMethods.cardCode!!,parametersMethods.cardExpirationDate!!)
        }
        if (type == "MercadoPago"){
            newPaymentMethod = MercadoPago(parametersMethods.user!!,parametersMethods.password!!)
        }

        if (type == "PayPal"){
            newPaymentMethod = PayPal(parametersMethods.user!!,parametersMethods.password!!)
        }

        return newPaymentMethod
    }

/*
    fun getOrderById(code: Int): OrderData {
        print(orders)
        var orderCorrect = orders.find { it.codeOrder == code }
        return orderCorrect
                ?: throw NotFoundResponse("No se encontró la orden con id $code")
    }
*/

}

