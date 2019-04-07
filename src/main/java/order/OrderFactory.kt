package order
import user.User
import restaurant.Restaurant
import productAndMenu.Menu
import paymentMethod.PaymentMethod

class OrderFactory {

    private var code : Int = 0

    fun createOrder(_user : User, _restaurant : Restaurant, payment : PaymentMethod, menus : MutableCollection<Menu>) : Order {
          var newOrder = Order(code , _user, _restaurant, payment, menus)
        code += 1
        //_user.addOrder(newOrder) Esto iria aca?
        return newOrder
    }
}