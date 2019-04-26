package applicationModel

import exception.NoUserFoundException
import geoclaseui.*
import user.*
import order.*
import paymentMethod.*
import restaurant.Restaurant
import productAndMenu.*
import searcher.Criteria
import searcher.Searcher
import java.util.*

object MorfApp {

    var restaurants: MutableMap<Int,Restaurant> = mutableMapOf();
    var registeredUsers: MutableMap<String,User> = mutableMapOf();
    var paymentMethods: MutableCollection<PaymentMethod> = mutableListOf(Cash(),
                                                                         CreditCard(),
                                                                         Debit(),
                                                                         MercadoPago(),
                                                                         PayPal());
    private var orderFactory : OrderFactory = OrderFactory;
    var clientFactory : ClientFactory = ClientFactory;
    private var restaurantFactory : RestaurantFactory  = RestaurantFactory;
    private var searcher : Searcher<Restaurant> = Searcher();
    var distance: Double = 20.00;

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
        this.registeredUsers.put(newClient.name,newClient);
    }

        fun createRestaurant(name: String,
                             description: String,
                             direction: String,
                             geoLocation: Geo,
                             availablePaymentMethods: MutableList<PaymentMethod>,
                             products: MutableSet<Product>,
                             menus: MutableSet<Menu>) {

        var newRestaurant: Restaurant = restaurantFactory.createRestaurant(name,
                                                                           description,
                                                                           direction,
                                                                           geoLocation,
                                                                           availablePaymentMethods,
                                                                           products,
                                                                           menus, this)
        this.restaurants.put(newRestaurant.code, newRestaurant);
        this.registeredUsers.put(newRestaurant.supervisor.name, newRestaurant.supervisor)
    }

    fun createOrder(client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu> ):Order{
        return orderFactory.createOrder(client , restaurant , paymentMethod , menus);
    }

    fun findRestaurant(criteria: Criteria<Restaurant>): MutableList<Restaurant?> {

            return this.searcher
                       .searchBy(criteria, this.restaurants);
    }

    fun findUser(name:String):User? {
        var actualUser: User?
        if (registeredUsers.contains(name)) {
            actualUser = registeredUsers.get(name)
        }
        else {
            throw NoUserFoundException("No se encuentra registrado el usuario en el sistema")
        }
        return actualUser
    }

    //TODO PREGUNTAR BUSQUEDA MIXTA DE DOS TIPOS DISTINTOS.
}