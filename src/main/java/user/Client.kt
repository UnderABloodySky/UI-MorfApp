package user

import restaurant.*
import productAndMenu.*
import order.*
import geoclaseui.*
import applicationModel.*
import paymentMethod.*
import scala.Tuple2
import java.util.*
class Client (code :Int,  id: String, var address: String, var registrationDate: Date,
              var geoLocation: Geo,  password : String, applicationModel: ApplicationModel )
                : User(code, id,password,applicationModel) {

    var ordersMade: MutableList<Order> = mutableListOf<Order>();

    var currentMenu: Menu? = null;

    fun makeNewOrder( restaurant: Restaurant,menus:MutableList<Menu>,paymentMethod: PaymentMethod){

        val newOrder: Order= applicationModel.createOrder(this, restaurant , paymentMethod, menus );
        ordersMade.add(newOrder);
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