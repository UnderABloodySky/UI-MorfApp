package user

import restaurant.*
import productAndMenu.*
import order.*
import geoclaseui.*
import applicationModel.*
import paymentMethod.*
import scala.Tuple2
import java.util.*

class Client (var address: String, var registrationDate: Date,
              var geoLocation: Geo, id: String, password:String,applicationModel: ApplicationModel )
                :User(id,password,applicationModel) {
    var applicationModel= ApplicationModel()
    var ordersMade: MutableList<Order> = mutableListOf<Order>();
    var currentMenu: Menu? = null;

    fun makeNewOrder( restaurant: Restaurant,menus:MutableList<Menu>,paymentMethod: PaymentMethod){

        val newOrder: Order= applicationModel.createOrder(this, restaurant , paymentMethod, menus );
        ordersMade.add(newOrder);
       // applicationModel.registerNewOrder(newOrder); creo que no hace falta

    }

    fun updateCurrentMenu( newMenu: Menu){
        currentMenu= newMenu;
    }

    fun  ordersMade():MutableList<Order> = ordersMade
    fun  currentMenu():Menu? = currentMenu;


}