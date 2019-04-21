package applicationModel

import exception.NoUserFoundException
import geoclaseui.Geo
import user.*
import order.*
import paymentMethod.*
import restaurant.*
import productAndMenu.*
import searcher.Criteria
import searcher.Searcher
import java.util.*

object ApplicationModel {

    var restaurants: MutableMap<Int, Restaurant> = mutableMapOf();
    var registeredUsers: MutableMap<String,User> = mutableMapOf();
    var paymentMethods: MutableCollection<PaymentMethod> = mutableListOf(Cash(),
                                                                         CreditCard(),
                                                                         Debit(),
                                                                         MercadoPago(),
                                                                         PayPal());
    private val orderFactory : OrderFactory = OrderFactory;
    private val clientFactory : ClientFactory = ClientFactory;
    private val restaurantFactory : RestaurantFactory  = RestaurantFactory;
    private val searcher : Searcher<Restaurant> = Searcher();
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

    fun findRestaurant(criteria: Criteria<Restaurant>): MutableList<Restaurant?> {

            return this.searcher
                       .searchBy(criteria, this.restaurants);
    }

    fun findUser(name:String):User?{

         if ( registeredUsers.contains(name)){
                return registeredUsers.get(name)
                 }
             throw NoUserFoundException("No se encuentra registrado el usuario en el sistema")
    }

    //TODO PREGUNTAR BUSQUEDA MIXTA DE DOS TIPOS DISTINTOS.
}