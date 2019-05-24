package user

import restaurant.*
import productAndMenu.*
import order.*
import applicationModel.*
import geoclase.*
import paymentMethod.*
import java.util.*

class Client (code :Int, name: String, id: String, var address: String, var registrationDate: Date,
              var geoLocation: Geo, password : String, applicationModel: MorfApp )
                : User(code, id, name,password,applicationModel) {

    var ordersMade: MutableList<Order> = mutableListOf()
    var currentOrder : Order? = null
    var currentMenu: Menu? = null

    fun makeNewOrder(restaurant : Restaurant, menus : MutableList<Menu>, paymentMethod : PaymentMethod){
        val newOrder: Order= applicationModel.createOrder(this, restaurant, paymentMethod, menus)
        ordersMade.add(newOrder)
    }

    fun restartCurrentOrder(){
        currentOrder = null;
    }

    fun addNewMenu(menu : Menu) = currentOrder?.addMenu(menu)

    fun updateCurrentMenu(newMenu: Menu){
        currentMenu= newMenu
    }

    fun  ordersMade():MutableList<Order> = ordersMade

    fun  currentMenu():Menu? = currentMenu

    fun addOrder(newOrder : Order) = ordersMade.add(newOrder)

    fun canDoOrder(_restaurant : Restaurant) : Boolean =
            GeoCalculator.distance(geoLocation, _restaurant.geoLocation) <= applicationModel.distance

}