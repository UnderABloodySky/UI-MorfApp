package applicationModel

import exception.NoUserAuthenticateException
import exception.UserAlreadyRegisteredException
import exception.UserNoFoundException
import geoclase.*
import user.*
import order.*
import paymentMethod.*
import restaurant.Restaurant
import productAndMenu.*
import searcher.Criteria
import searcher.Searcher
import searcher.Searchable

object MorfApp {
    var restaurants= mutableMapOf<Int,Restaurant>()
    var registeredUsers = mutableMapOf<String,User>()
   // var paymentMethods = mutableListOf(Cash(),CreditCard(),Debit(), MercadoPago(), PayPal())
    private var orderFactory = OrderFactory()
    var clientFactory = ClientFactory()
    private var restaurantFactory  = RestaurantFactory()
    private var searcher = Searcher()
    var distance = 20.00

    fun createClient(id : String, name: String, address: String, geoLocation : Geo,  password : String, email : String): Client {
        if(!registeredUsers.containsKey(id)){
            val newClient: Client = clientFactory.createClient(address,
                    geoLocation,
                    name,
                    id,
                    password,
                    email)

            this.registeredUsers.put(newClient.id,newClient)
            return newClient
        }
        else {throw UserAlreadyRegisteredException("Ya se encuentra registrado el usuario de ID: $id")
        }
    }

    fun createSupervisor(restaurant: Restaurant, id: String, name: String, password: String): Supervisor {
        if(!isCorrectID(id)){
            throw UserAlreadyRegisteredException("Ya se encuentra registrado el supervisor de ID: $id")
        }
        val newSupervisor: Supervisor = clientFactory.createSupervisor(restaurant,
                                                  name,
                                                  id,
                                                 password)

                this.registeredUsers.put(newSupervisor.name,newSupervisor)
                restaurant.addSupervisor(newSupervisor)
                return newSupervisor
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
    fun findClient(name:String?):Client? {
        return this.findUser(name)as(Client)

    }


    fun authenticate(name : String, aPass : String) : Client?{
        val aUser = registeredUsers.get(name)
        if(aUser == null  || !aUser!!.isCorrectPassword(aPass)){
            throw NoUserAuthenticateException("Usuario o Contraseña incorrecto")
        }
        return aUser as Client
    }

    private fun isCorrectID(id : String) = !registeredUsers.containsKey(id)
}
