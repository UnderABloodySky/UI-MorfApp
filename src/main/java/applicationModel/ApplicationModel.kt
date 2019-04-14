package applicationModel

import exception.NoRestaurantFoundException
import geoclaseui.Geo
import user.*
import order.*
import paymentMethod.*
import restaurant.*
import productAndMenu.*
import java.util.*
import kotlin.NoSuchElementException

object ApplicationModel {

    var restaurants: MutableMap<Int, Restaurant> = mutableMapOf();
    var registeredUsers: MutableCollection<User> = mutableListOf();
    var paymentMethods: MutableCollection<PaymentMethod> = mutableListOf(Cash(),
                                                                         CreditCard(),
                                                                         Debit(),
                                                                         MercadoPago(),
                                                                         PayPal());
    private val orderFactory : OrderFactory = OrderFactory;
    private val clientFactory : ClientFactory = ClientFactory;
    private val restaurantFactory : RestaurantFactory  = RestaurantFactory;

        fun createClient(address: String,
                     registrationDate: Date,
                     geoLocation: Geo,
                     id : String,
                     password: String): Unit {

                var newClient: User = clientFactory.createClient(address,
                                               registrationDate,
                                               geoLocation,
                                               id,
                                               password,

                                               this)
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

        var newRestaurant: Restaurant = restaurantFactory.createRestaurant(name,
                                                                           description,
                                                                           supervisor,
                                                                           direction,
                                                                           geoLocation,
                                                                           availablePaymentMethods,
                                                                           products,
                                                                           menus)
        this.restaurants.put(newRestaurant.code, newRestaurant);

    }

    fun createOrder(client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu> ):Order{
        return orderFactory.createOrder(client , restaurant , paymentMethod , menus);
    }

    fun changeSupervisorInRestaurant(restaurant: Restaurant,supervisor: Supervisor){
        restaurant.changeSupervisor(supervisor);
    }

    fun findRestaurantById(id: Int): Restaurant {

        return this.restaurants.getValue(id);
    }

    fun findRestaurantByName(name: String): Restaurant {
        var foundResto: Restaurant? = null;
        this.restaurants.forEach {
            if (it.value.name.contains(name))
                foundResto = it.value;
        }
        if (foundResto == null)
            throw NoRestaurantFoundException("No restaurant was found that matches that search");
        else
            return foundResto as Restaurant;
    }
}