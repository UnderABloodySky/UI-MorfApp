package applicationModel

import exception.UserAlreadyRegisteredException
import exception.UserNoFoundException
import geoclase.Geo
import user.*
import order.*
import paymentMethod.*
import restaurant.Restaurant
import productAndMenu.*
import searcher.Criteria
import searcher.Searcher
import searcher.Searchable

import java.util.*

object MorfApp {
    var restaurants: MutableMap<Int,Restaurant> = mutableMapOf()
    var registeredUsers: MutableMap<String,User> = mutableMapOf()
    var paymentMethods: MutableCollection<PaymentMethod> = mutableListOf(Cash(),
                                                                         CreditCard(),
                                                                         Debit(),
                                                                         MercadoPago(),
                                                                         PayPal())
    private var orderFactory : OrderFactory = OrderFactory()
    var clientFactory : ClientFactory = ClientFactory()
    private var restaurantFactory : RestaurantFactory  = RestaurantFactory()
    private var searcher : Searcher = Searcher()
    var distance: Double = 20.00

    fun createClient(id : String, name: String, address: String, geoLocation : Geo, password : String): Client {

        if(!registeredUsers.containsKey(id)){
            var today : Date = Date()
            val newClient: Client = clientFactory.createClient(address,
                    today,
                    geoLocation,
                    name,
                    id,
                    password,
                    this)

            this.registeredUsers.put(newClient.name,newClient)
            return newClient
        }
        else {throw UserAlreadyRegisteredException("Ya se encuentra registrado el usuario")}
    }

    fun createSupervisor(restaurant: Restaurant, id: String, name: String, password: String): Supervisor {

        if(!registeredUsers.containsKey(id)){
                val newSupervisor: Supervisor = clientFactory.createSupervisor(restaurant,
                                                  name,
                                                  id,
                                                 password,
                                               this)

                this.registeredUsers.put(newSupervisor.name,newSupervisor)
                restaurant.addSupervisor(newSupervisor)
                return newSupervisor
            }
        else {throw UserAlreadyRegisteredException("Ya se encuentra registrado el usuario")}
    }
    fun createRestaurant(name: String,
                             description: String,
                             direction: String,
                             geoLocation: Geo,
                             availablePaymentMethods: MutableList<PaymentMethod>) : Restaurant{

        var newRestaurant: Restaurant = restaurantFactory.createRestaurant(name,
                                                                         description,
                                                                           direction,
                                                                           geoLocation,
                                                                           availablePaymentMethods)
        this.restaurants.put(newRestaurant.code, newRestaurant)
        return newRestaurant
    }

    fun createOrder(client:Client, restaurant:Restaurant , paymentMethod:PaymentMethod, menus:MutableList<Menu> ):Order{
        return orderFactory.createOrder(client , restaurant , paymentMethod , menus)
    }

    fun findRestaurant(criteria: Criteria): MutableCollection<Searchable?>{
            var searchableRestaurant = restaurants as MutableMap<Int, Searchable>
            return this.searcher
                       .searchBy(criteria, searchableRestaurant)}

    fun findUser(name:String?):User? {
        val actualUser: User?
        if (name!=null && registeredUsers.contains(name)) {
            actualUser = registeredUsers.get(name)
        }
        else { throw UserNoFoundException("ERROR") }
        return actualUser
    }
 }
