package user

import restaurant.*
import productAndMenu.*
import order.*
import geoclaseui.*
import applicationModel.*
import paymentMethod.*
import scala.Tuple2
import java.util.*
class Client (code :Int,  name: String, var address: String, var registrationDate: Date,
              var geoLocation: Geo,  password : String, applicationModel: ApplicationModel )
                : User(code, name,password,applicationModel) {

    var ordersMade: MutableList<Order> = mutableListOf<Order>();
    var currentOrder : Order? = null;
    //Por que agregamos esto?
    var currentMenu: Menu? = null;

    //No tendriamos que agregarlo ahi. Las OrdersMade serian ordenes ya entregadas.
    fun makeNewOrder(restaurant : Restaurant, menus : MutableList<Menu>, paymentMethod : PaymentMethod){
        val newOrder: Order= applicationModel.createOrder(this, restaurant, paymentMethod, menus);
        ordersMade.add(newOrder);

        //currentOrder = applicationModel.createOrder(this, restaurant , paymentMethod, menus )
    }

    fun restartCurrentOrder() : Unit{
        currentOrder = null;
    }

    fun addNewMenu(menu : Menu) : Unit{
        currentOrder?.addMenu(menu)
    }

    fun updateCurrentMenu( newMenu: Menu){
        currentMenu= newMenu;
    }

    fun  ordersMade():MutableList<Order> = ordersMade

    fun  currentMenu():Menu? = currentMenu;

    fun addOrder(newOrder : Order) : Unit {
        ordersMade.add(newOrder)
    }

    fun canDoOrder(_restaurant : Restaurant) : Boolean = GeoCalculator.distance(geoLocation, _restaurant.geoLocation) <= applicationModel.distance

}