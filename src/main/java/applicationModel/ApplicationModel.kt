package applicationModel
import user.*
import order.*
import paymentMethod.PaymentMethod
import restaurant.*
import productAndMenu.*
class ApplicationModel {


    fun createOrder(client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu> ):Order{
        return OrderFactory().createOrder(client , restaurant , paymentMethod , menus );
    }
    fun changeSupervisorInRestaurant(restaurant: Restaurant,supervisor: Supervisor){
        restaurant.changeSupervisor(supervisor);

    }
}