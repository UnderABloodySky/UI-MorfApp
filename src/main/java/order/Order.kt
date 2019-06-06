package order

import com.fasterxml.jackson.annotation.JsonIgnore
import exception.EmptyOrderException
import exception.NoValidateOrderException
import statesOrder.StateOrder
import statesOrder.StateOrder.*
import paymentMethod.*
import java.util.*
import restaurant.Restaurant
import productAndMenu.Menu
import scala.Tuple2
import user.Client

data class Order(val code : Int, @JsonIgnore private val user : Client,
                 @JsonIgnore private val restaurant : Restaurant, private var payment : PaymentMethod,
                 private val menus : MutableList<Menu>){
    var geoLocation = user.geoLocation
    var restaurantName = restaurant.name
    var rate:Int = 0

    private var date = Date()
    private var state : StateOrder = PENDING

    fun processOrder() {
        if (menus.isEmpty()) {
            throw EmptyOrderException("La orden debe contener al menos un menu")
        }
        restaurant.addOrder(this)
    }

    fun updateRate(newRate:Int){
        rate = newRate
    }
    fun addMenu(_new_menu : Menu) {
        if(!canProcessOrder(_new_menu)) {
            throw NoValidateOrderException("")
        }
        menus.add(_new_menu)
    }

    fun removeMenu(_menu : Menu) {
        menus.remove(_menu)
    }

    fun price() : Double = menus.map{ menu -> menu.totalPrice() }.sum()

    fun setState(_state : StateOrder) {
        state = _state
    }

    fun menus() : MutableCollection<Menu> = menus

    fun getState() : StateOrder = state

    fun delivered() {
        user.removePendingOrder(this)
        user.addDeliveredOrder(this)
        setState(DELIVERED)
    }

    fun pending() {
        setState(PENDING)
    }

    fun onMyWay() {
        setState(ONMYWAY)
    }

    fun cancelled() {
        setState(CANCELLED)
    }

    fun getPaymentMethod(): PaymentMethod = payment

    fun setPaymentMethod(_payment: PaymentMethod) {
        if (canChange()) {
            payment = _payment
        }
    }

    fun canChange(): Boolean = state.canChange()

    fun getUser() : Client = user

    fun getRestaurant() : Restaurant = restaurant

    fun getMenu() : MutableList<Menu> = menus

    fun getMenusAndCuantity():MutableMap<Int,Int> {

        var ids = mutableSetOf<Int>()

        menus.forEach { menu-> ids.add(menu.code)  }
        var finalList = mutableMapOf<Int,Int>()
        ids.forEach{id->
                        finalList.put(id,(this.appearencesOfId(id,menus)))
                }
        return finalList
    }

    fun appearencesOfId(id:Int, list:MutableList<Menu>):Int{
        var  quantity =0
        list.forEach { menu-> if (menu.code==id){
            quantity++
           }
        }
        return quantity;
    }

    private fun canProcessOrder(_menu : Menu) : Boolean = user.canDoOrder(_menu.restaurant)
}
