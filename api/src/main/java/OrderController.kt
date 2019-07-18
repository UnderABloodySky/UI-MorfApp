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
import user.Client
import java.util.*

data class RateData(var code_order:Int,var rating:Int,var id:String)
data class Geo(var lat:Double,var long:Double)
data class MenusAndAmount(var menuId:Int,var ammount:Int)
data class PaymentMethodsParameters(var type:String, var user:String?,var password:String?,
                                    var cardNumber:String?,var cardOwnerName:String?,var cardExpirationDate:String?,var cardCode:String?)

data class OrderData(var restaurant:Int,var menus: MutableList<MenusAndAmount>,
                     var clientID:String,var paymentMethod: PaymentMethodsParameters) {
    var ratingData: Int = 0
}
    //creo este data para que devolverlo con el metodo de pago entero y no la villereada de arriba
    data class OrderDataComplete(var code_order_complete: Int, var restaurant: Int, var restaurantName: String, var menus: MutableList<DataMenuInOrder>,
                                 var clientID: String, var paymentMethod: PaymentMethod) {
        var ratingData: Int = 0
    }


    class OrderController() {
        private var orders = mutableListOf<OrderData>()
        private val morfApp = MorfApp;


        //refactorizar esto. ver por que no está handleando el error
        fun transformOrdersToOrderData(order: MutableList<Order>): MutableList<OrderDataComplete> {
            var orderDatas = mutableListOf<OrderDataComplete>()
            order.forEach { o ->
                var orderDataNew = OrderDataComplete(o.code, o.getRestaurant().code, o.restaurantName,
                        o.getMenusAndCuantity(),
                        o.getUser().id, o.getPaymentMethod())
                orderDatas.add(orderDataNew)
            }
            return orderDatas

        }


        //la idea es que reciba lo que le viene por el json y devuelva la lista piola de los menus
        fun transformToMenuList(idsAccumulate: MutableList<MenusAndAmount>, resto: Restaurant): MutableList<Menu> {
            var allMenus = resto.menus()
            var newMenus = mutableListOf<Menu>()
            idsAccumulate.forEach { m ->
                var menuToAdd = allMenus.get(m.menuId)!!
                newMenus.add(menuToAdd)

            }
            return newMenus
        }


        fun historicOrders(ctx: Context) {
            val userId = ctx.pathParam("id")
            var client = try {
                morfApp.findClient(userId)
            } catch (e: Exception) {
                throw NotFoundResponse("No se encontró la orden con id $userId")
            }

            var ordersDataComplete = this.transformOrdersToOrderData(client!!.historicOrders)
            ctx.status(HttpStatus.OK_200)
            ctx.json(ordersDataComplete)
        }

        fun isNotNull(nullable: Any?): Boolean {
            return nullable != null
        }

        //hacer que esta mierda tome un data y cree un order .
        fun rateAnOrder(ctx: Context) {
            val rateData = ctx.body<RateData>()
            var orderCode = rateData.code_order
            var rate = rateData.rating

            val codeUser = ctx.pathParam("id")
            //val codeOrder = ctx.pathParam("code_order")!!.toInt()


            if (this.isNotNull(orderCode)) {

                var client = morfApp.findClient(codeUser)
                        ?: throw NotFoundResponse("No se encontró el usuario con id $codeUser")
                var orderToUpdate = client.findPendingOrderInCollection(orderCode)
                        ?: throw NotFoundResponse("No se encontró la orden con id $orderCode")
                print(orderCode)
                client.rateOrder(orderToUpdate, rate)

                var orderDataComplete = OrderDataComplete(orderToUpdate.code, orderToUpdate.getRestaurant().code,
                        orderToUpdate.getRestaurant().name,
                        orderToUpdate.getMenusAndCuantity(),
                        orderToUpdate.getUser().id,
                        orderToUpdate.getPaymentMethod())
                orderDataComplete.ratingData = rate
                this.changeStatusFromPendingToHistoric(orderDataComplete, client)

                ctx.status(HttpStatus.CREATED_201)
                ctx.json(orderDataComplete)
            }
        }

        fun changeStatusFromPendingToHistoric(orderDataComplete: OrderDataComplete, client: Client) {
            client.moveToHistoricOrder(orderDataComplete.code_order_complete)

        }

        fun pendingOrders(ctx: Context) {
            val userId = ctx.pathParam("id")
            var client = morfApp.findClient(userId) ?: throw NotFoundResponse("No se encontró la orden con id $userId")
            print(client.pendingOrders)
            var ordersDataComplete = this.transformOrdersToOrderData(client.pendingOrders)
            ctx.status(HttpStatus.OK_200)
            ctx.json(ordersDataComplete)
        }


        fun addOrder(ctx: Context) {
            val order = ctx.body<OrderData>()
            val client = morfApp.findClient(order.clientID)!!
            var paymentMethod = this.createPaymentMethodApropieted(order.paymentMethod)
            var restaurant: Restaurant? = morfApp.findOtherRestaurant(order.restaurant)
            var menus = this.transformToMenuList(order.menus, restaurant!!)
            client.makeNewOrder(restaurant, menus, paymentMethod)

            ctx.status(HttpStatus.CREATED_201)
        }


        //funciones complementarias

        fun createPaymentMethodApropieted(parametersMethods: PaymentMethodsParameters): PaymentMethod {
            val type = parametersMethods.type
            var newPaymentMethod: PaymentMethod = paymentMethod.Cash()
            if (type == "CreditCard") {
                newPaymentMethod = CreditCard(parametersMethods.cardOwnerName!!, parametersMethods.cardNumber!!, parametersMethods.cardCode!!, parametersMethods.cardExpirationDate!!)
            }
            if (type == "DebitCard") {
                newPaymentMethod = Debit(parametersMethods.cardOwnerName!!, parametersMethods.cardNumber!!, parametersMethods.cardCode!!, parametersMethods.cardExpirationDate!!)
            }
            if (type == "MercadoPago") {
                newPaymentMethod = MercadoPago(parametersMethods.user!!, parametersMethods.password!!)
            }

            if (type == "PayPal") {
                newPaymentMethod = PayPal(parametersMethods.user!!, parametersMethods.password!!)
            }

            return newPaymentMethod
        }

    }

