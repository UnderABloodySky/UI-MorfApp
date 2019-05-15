package applicationModel

import discount.Discount
import geoclaseui.Geo
import order.Order
import restaurant.Restaurant
import productAndMenu.Menu
import paymentMethod.PaymentMethod
import productAndMenu.Category
import productAndMenu.Product
import user.Client
import user.Supervisor
import java.util.*

abstract class GeneralFactory{
    protected var code : Int = 1
    protected fun addOne() = code++
    fun code() : Int = code
}

class OrderFactory : GeneralFactory(){
    fun createOrder(user: Client,
                    restaurant: Restaurant,
                    payment: PaymentMethod,
                    menus: MutableCollection<Menu>): Order {

        val newOrder = Order(code, user, restaurant, payment, menus)
        addOne()
        return newOrder
    }
}

class ProductFactory : GeneralFactory(){
    fun createProduct(name : String, description : String, price : Double, category : Category) : Product{
        val newProduct = Product(code, name, description, price, category)
        addOne()
        return newProduct
    }
}

class MenuFactory : GeneralFactory(){
        fun createMenu(name : String, description : String, products : MutableList<Product>, restaurant : Restaurant, discount : Discount, enabled : Boolean) : Menu{
            val newMenu = Menu(code, name, description, products, restaurant, discount, enabled)
            addOne()
            return newMenu
        }
}

class ClientFactory : GeneralFactory() {

        fun createClient(address: String,
                     registrationDate: Date,
                     geoLocation: Geo,
                     name : String,
                     id : String,
                     password: String,
                     applicationModel: MorfApp): Client {

        val newClient = Client(code, name, id, address, registrationDate, geoLocation,  password, applicationModel)
        addOne()
        return newClient
    }

    fun createSupervisor(restaurant: Restaurant,
                         name : String,
                         id : String,
                         password: String,
                         applicationModel: MorfApp): Supervisor{

        val newSupervisor = Supervisor(code, name, id , restaurant,password, applicationModel)
        addOne()
        return newSupervisor
    }
}

class RestaurantFactory : GeneralFactory(){

    fun createRestaurant(name: String,
                         description: String,
                         direction: String,
                         geoLocation: Geo,
                         availablePaymentMethods: MutableCollection<PaymentMethod>): Restaurant {

        val newRestaurant = Restaurant(code,
                                       name,
                                       description,
                                       direction,
                                       geoLocation,
                                       availablePaymentMethods)
        addOne()
        return newRestaurant
    }
}


