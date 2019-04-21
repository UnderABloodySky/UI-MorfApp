package applicationModel

import geoclaseui.Geo
import order.Order
import user.User
import restaurant.Restaurant
import productAndMenu.Menu
import paymentMethod.PaymentMethod
import productAndMenu.Product
import user.Client
import user.Supervisor
import java.util.*

object OrderFactory {
    private var code: Int = 0

    fun createOrder(user: Client,
                    restaurant: Restaurant,
                    payment: PaymentMethod,
                    menus: MutableCollection<Menu>): Order {

        var newOrder = Order(code, user, restaurant, payment, menus);
        code++;

        return newOrder;
    }
}

object ClientFactory {
    private var code: Int = 0;


//    class Client (code :Int,  id: String, var address: String, var registrationDate: Date,
//                  var geoLocation: Geo,  password : String, applicationModel: ApplicationModel )
//        : User(code, id,password,applicationModel) {


        fun createClient(address: String,
                     registrationDate: Date,
                     geoLocation: Geo,
                     id : String,
                     password: String,
                     applicationModel: ApplicationModel): User {

        var newClient = Client(code, id, address, registrationDate, geoLocation,  password, applicationModel);
        code++;
        return newClient;
    }



    fun createSupervisor(restaurant: Restaurant,
                         id : String,
                         password: String,
                         applicationModel: ApplicationModel): User{

        var newSupervisor = Supervisor(code, id , restaurant,password, applicationModel);
        return newSupervisor;
        code++;
    }
}

object RestaurantFactory {
    private var code: Int = 0;

    fun createRestaurant(name: String,
                         description: String,
                         supervisor : Supervisor,
                         direction: String,
                         geoLocation: Geo,
                         availablePaymentMethods: MutableList<PaymentMethod>,
                         products: MutableSet<Product>,
                         menus: MutableSet<Menu>): Restaurant {

        var newRestaurant = Restaurant(code,
                                       name,
                                       description,
                                       direction,
                                       geoLocation);
        code++;

        return newRestaurant;
    }
}


