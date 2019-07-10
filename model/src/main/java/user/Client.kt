package user

import restaurant.*
import productAndMenu.*
import order.*
import geoclase.*
import applicationModel.*
import com.fasterxml.jackson.annotation.JsonIgnore
import paymentMethod.*
import java.util.*
class Client (code :Int,  name: String, id: String, var address: String,
              var geoLocation: Geo,  password : String, var email : String)
                : User(code, id, name,password) {

    var pendingOrders: MutableList<Order> = mutableListOf()
    var registrationDate: Date = Date()
    var historicOrders = mutableListOf<Order>()

    var currentOrder : Order? = null
    var currentMenu: Menu? = null

    fun makeNewOrder(restaurant : Restaurant, menus : MutableList<Menu>, paymentMethod : PaymentMethod) : Order{

        val newOrder= applicationModel.createOrder(this, restaurant, paymentMethod, menus)
        pendingOrders.add(newOrder)
        return newOrder
    }

    fun restartCurrentOrder(){
        currentOrder = null;
    }

    fun moveToHistoricOrder(orderId:Int){
       var orderToAddHistoric =pendingOrders.firstOrNull {order-> order.code == orderId}

        if (orderToAddHistoric!=null){
            historicOrders.add(orderToAddHistoric)
            pendingOrders.remove(orderToAddHistoric)

        }

    }
    fun addNewMenu(menu : Menu) = currentOrder?.addMenu(menu)

    fun updateCurrentMenu(newMenu: Menu){
        currentMenu= newMenu
    }

    fun  ordersMade():MutableList<Order> = pendingOrders

    fun  currentMenu():Menu? = currentMenu

    fun addOrder(newOrder : Order) = pendingOrders.add(newOrder)

    fun addDeliveredOrder(newOrder : Order) = historicOrders.add(newOrder)

    fun canDoOrder(_restaurant : Restaurant) : Boolean =
            GeoCalculator.distance(geoLocation, _restaurant.geoLocation) <= applicationModel.distance

    fun removePendingOrder(aOrder : Order){
        pendingOrders.remove(aOrder)
    }
    fun findPendingOrderInCollection(codeOrder:Int):Order?{
        return  pendingOrders.firstOrNull { order -> order.code == codeOrder }
    }

    fun findOrderInCollection(updatedCodeOrder:Int):Order? {

        return  historicOrders.firstOrNull { order -> order.code == updatedCodeOrder }
    }

    fun rateOrder(updatedOrder:Order,rate:Int){
        var order:Order
        order  = this.findPendingOrderInCollection(updatedOrder.code)!!
        order.updateRate(rate)
        }
    }
