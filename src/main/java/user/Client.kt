package user

import restaurant.*
import productAndMenu.*
import order.*
import geoclase.*
import applicationModel.*
import paymentMethod.*
import java.util.*
class Client (code :Int,  name: String, id: String, var address: String,
              var geoLocation: Geo,  password : String, var email : String, applicationModel: MorfApp )
                : User(code, id, name,password,applicationModel) {

    var pendingOrders: MutableList<Order> = mutableListOf()
    var registrationDate: Date = Date()
    var historicOrders = mutableListOf<Order>()

    var currentOrder : Order? = null
    var currentMenu: Menu? = null

    fun makeNewOrder(restaurant : Restaurant, menus : MutableList<Menu>, paymentMethod : PaymentMethod){
        val newOrder: Order= applicationModel.createOrder(this, restaurant, paymentMethod, menus)
        pendingOrders.add(newOrder)
    }

    fun restartCurrentOrder(){
        currentOrder = null;
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

}