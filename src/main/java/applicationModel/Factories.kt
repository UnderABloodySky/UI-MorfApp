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

    fun createOrder(user: User,
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


    fun createClient(address: String,
                     registrationDate: Date,
                     geoLocation: Geo,
                     password: String,
                     applicationModel: ApplicationModel): User {

        var newClient = Client(address, registrationDate, geoLocation, code, password, applicationModel);
        code++;

        return newClient;
    }
}

object RestaurantFactory {
    private var code: Int = 0;

    fun createRestaurant(name: String,
                         description: String,
                         supervisor: Supervisor,
                         direction: String,
                         geoLocation: Geo,
                         availablePaymentMethods: MutableList<PaymentMethod>,
                         products: MutableSet<Product>,
                         menus: MutableSet<Menu>): Restaurant {

        var newRestaurant = Restaurant(code,
                                       name,
                                       description,
                                       supervisor,
                                       direction,
                                       geoLocation,
                                       availablePaymentMethods,
                                       products,
                                       menus);
        code++;

        return newRestaurant;
    }
}


