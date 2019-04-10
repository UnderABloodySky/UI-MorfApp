package order

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import paymentMethod.Cash
import paymentMethod.PaymentMethod
import productAndMenu.Menu
import restaurant.Restaurant
import statesOrder.StateOrder
import user.User

class TestOrder {
    private val dummyUser = Mockito.mock(User::class.java)
    private val dummyMenu0 = Mockito.mock(Menu::class.java)
    private val dummyMenu1 = Mockito.mock(Menu::class.java)
    private val dummyMenu2 = Mockito.mock(Menu::class.java)
    private val dummyMenu3 = Mockito.mock(Menu::class.java)
    private val dummyRestaurant = Mockito.mock(Restaurant::class.java)
    private val cash : PaymentMethod = Cash()
    private val menus : MutableCollection<Menu> = mutableListOf<Menu>()
    private val order : Order = Order(2,dummyUser ,dummyRestaurant, cash, menus)

    @Test
    fun addMenuAddsMenusInTheOrdersCollection() {
        Assert.assertEquals(0, menus.size)
        order.addMenu(dummyMenu0)
        Assert.assertEquals(1, menus.size)
        order.addMenu(dummyMenu1)
        Assert.assertEquals(2, menus.size)
        order.addMenu(dummyMenu2)
        Assert.assertEquals(3, menus.size)
        order.addMenu(dummyMenu3)
        Assert.assertEquals(4, menus.size)
    }

    @Test
    fun removeMenuRemovesMenusInTheOrdersCollection() {
        order.addMenu(dummyMenu0)
        order.addMenu(dummyMenu1)
        order.addMenu(dummyMenu2)
        order.addMenu(dummyMenu3)

        Assert.assertEquals(4, menus.size)
        order.removeMenu(dummyMenu3)
        Assert.assertEquals(3, menus.size)
        order.removeMenu(dummyMenu2)
        Assert.assertEquals(2, menus.size)
        order.removeMenu(dummyMenu1)
        Assert.assertEquals(1, menus.size)
        order.addMenu(dummyMenu0)
        Assert.assertEquals(0, menus.size)
    }

    @Test
    fun delivered() {
        order.delivered()
        Assert.assertEquals(StateOrder.DELIVERED,order.getState())
    }

    @Test
    fun pending() {
        order.pending()
        Assert.assertEquals(StateOrder.PENDING,order.getState())
    }

    @Test
    fun onMyWay() {
        order.onMyWay()
        Assert.assertEquals(StateOrder.ONMYWAY,order.getState())
    }

    @Test
    fun cancelled() {
        order.delivered()
        Assert.assertEquals(StateOrder.CANCELLED,order.getState())
    }
}