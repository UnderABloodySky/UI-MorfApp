package applicationModel

import discount.Discount
import geoclaseui.Geo
import order.Order
import restaurant.Restaurant
import productAndMenu.Menu
import paymentMethod.PaymentMethod
import productAndMenu.Category
import productAndMenu.Product
import sun.java2d.loops.FillRect
import user.Client
import user.Supervisor
import java.util.*

abstract class GeneralFactory{
    var code : Int = 0

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

class ProductFactory : GeneralFactory(){

    fun createProduct(name : String, description : String, price : Double, category : Category) : Product{
        var newProduct = Product(code, name, description, price, category)
        addOne()
        return newProduct
    }
}

class MenuFactory : GeneralFactory(){
        fun createMenu(name : String, description : String, products : MutableCollection<Product>, restaurant : Restaurant, discount : Discount, enabled : Boolean) : Menu{
            var newMenu = Menu(code, name, description, products, restaurant, discount, enabled)
            addOne()
            return newMenu
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
                         availablePaymentMethods: MutableCollection<PaymentMethod>): Restaurant {

        var newRestaurant = Restaurant(code,
                                       name,
                                       description,
                                       direction,
                                       geoLocation,
                                       availablePaymentMethods);
        addOne();
        return newRestaurant;
    }
}


