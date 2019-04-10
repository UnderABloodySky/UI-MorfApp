package applicationModel
import geoclaseui.Geo
import user.*
import order.*
import paymentMethod.*
import restaurant.*
import productAndMenu.*
import java.util.*

object ApplicationModel {

    var restaurants: MutableCollection<Restaurant> = mutableListOf();
    var registeredUsers: MutableCollection<User> = mutableListOf();
    var paymentMethods: MutableCollection<PaymentMethod> = mutableListOf(Cash(),
                                                                         CreditCard(),
                                                                         Debit(),
                                                                         MercadoPago(),
                                                                         PayPal());
    private val orderFactory : OrderFactory = OrderFactory;
    private val clientFactory : ClientFactory = ClientFactory;


    fun createClient(address: String,
                     registrationDate: Date,
                     geoLocation: Geo,
                     password: String): Unit {

        var newClient: User = clientFactory.createClient(address,
                                               registrationDate,
                                               geoLocation,
                                               password,
                                               this);
        this.registeredUsers.add(newClient);
    }
    fun createRestaurant(name: String,
                         description: String,
                         supervisor: Supervisor,
                         direction: String,
                         geoLocation: Geo,
                         availablePaymentMethods: MutableList<PaymentMethod>,
                         products: MutableSet<Product>,
                         menus: MutableSet<Menu>) {

        var newRestaurant: Restaurant = RestaurantFactory.createRestaurant(name,
                                                                           description,
                                                                           supervisor,
                                                                           direction,
                                                                           geoLocation,
                                                                           availablePaymentMethods,
                                                                           products,
                                                                           menus)
        this.restaurants.add(newRestaurant);
    }

    fun createOrder(client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu> ):Order{
        return orderFactory.createOrder(client , restaurant , paymentMethod , menus);
    }

    fun changeSupervisorInRestaurant(restaurant: Restaurant,supervisor: Supervisor){
        restaurant.changeSupervisor(supervisor);
    }
}