package order

import statesOrder.StateOrder
import statesOrder.StateOrder.*
import paymentMethod.*
import java.util.*
import user.User
import restaurant.Restaurant
import productAndMenu.Menu
import user.Client

data class Order(private val code : Int, private val user : Client,
                 private val restaurant : Restaurant, private var payment : PaymentMethod,
                 private val menus : MutableCollection<Menu>){

    private var state : StateOrder = PENDING
    private var date : Date? = null

    fun addMenu(_new_menu : Menu): Unit {
        menus.add(_new_menu)
    }

    fun removeMenu(_menu : Menu) : Unit{
        menus.remove(_menu)
    }

    fun price() : Double = menus.map{ menu -> menu.totalPrice() }.sum()

    fun setState(_state : StateOrder) : Unit{
        state = _state
    }

    fun menus() : MutableCollection<Menu> = menus

    fun getState() : StateOrder = state

    fun delivered() : Unit{
        user.addOrder(this);
        setState(DELIVERED)
    }

    fun pending() : Unit{
        setState(PENDING)
    }

    fun onMyWay() : Unit{
        setState(ONMYWAY)
    }

    fun cancelled() : Unit{
        setState(CANCELLED)
    }

    fun getPaymentMethod(): PaymentMethod = payment

    fun setPaymentMethod(_payment: PaymentMethod) : Unit {
        if (canChange()) {
            payment = _payment
        }
    }

    fun canChange(): Boolean = state.canChange()

    fun getUser() : User = user

    fun getRestaurant() : Restaurant = restaurant

    fun getMenu() : MutableCollection<Menu> = menus
}