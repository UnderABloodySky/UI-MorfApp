package applicationModel

import geoclaseui.Geo
import order.Order
import restaurant.Restaurant
import productAndMenu.Menu
import paymentMethod.PaymentMethod
import productAndMenu.Product
import user.Client
import user.Supervisor
import java.util.*

abstract class GeneralFactory{
    protected var code : Int = 0

    protected fun addOne() : Unit {
        code++;
    }
}

object OrderFactory : GeneralFactory(){

     fun createOrder(user: Client,
                    restaurant: Restaurant,
                    payment: PaymentMethod,
                    menus: MutableCollection<Menu>): Order {

        var newOrder = Order(code, user, restaurant, payment, menus);
        addOne();
        return newOrder;
    }
}

object ClientFactory : GeneralFactory() {

        fun createClient(address: String,
                     registrationDate: Date,
                     geoLocation: Geo,
                     id : String,
                     password: String,
                     applicationModel: MorfApp): Client {

        var newClient = Client(code, id, address, registrationDate, geoLocation,  password, applicationModel);
        addOne();
        return newClient;
    }

    fun createSupervisor(restaurant: Restaurant,
                         id : String,
                         password: String,
                         applicationModel: MorfApp): Supervisor{

        var newSupervisor = Supervisor(code, id , restaurant,password, applicationModel);
        addOne();
        return newSupervisor;
    }
}

object RestaurantFactory : GeneralFactory(){

    fun createRestaurant(name: String,
                         description: String,
                         direction: String,
                         geoLocation: Geo,
                         availablePaymentMethods: MutableList<PaymentMethod>,
                         products: MutableSet<Product>,
                         menus: MutableSet<Menu>,
                         applicationModel: MorfApp): Restaurant {

        var newRestaurant = Restaurant(code,
                                       name,
                                       description,
                                       direction,
                                       geoLocation,
                                       applicationModel);
        addOne();
        return newRestaurant;
    }
}


