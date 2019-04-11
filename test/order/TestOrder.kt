package order

import applicationModel.ApplicationModel
import geoclaseui.Geo
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import paymentMethod.Cash
import paymentMethod.PaymentMethod
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant
import statesOrder.StateOrder
import user.Client
import user.Supervisor
import user.User
import java.util.*

class TestOrder {
    //private val dummyUser = Mockito.mock(User::class.java)
    //private val dummyMenu0 = Mockito.mock(Menu::class.java)
    //private val dummyMenu1 = Mockito.mock(Menu::class.java)
    //private val dummyMenu2 = Mockito.mock(Menu::class.java)
    //private val dummyMenu3 = Mockito.mock(Menu::class.java)
    //private val dummyRestaurant = Mockito.mock(Restaurant::class.java)
    private val cash : PaymentMethod = Cash()
    private val menus : MutableCollection<Menu> = mutableListOf<Menu>()
    //private val orderMock : Order = Order(2,dummyUser ,dummyRestaurant, cash, menus)

    private var menu0 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);
    private var menu1 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);
    private var menu2 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);
    private var menu3 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);

    private var geoLocation: Geo = Geo(1.2,2.2);
    private var applicationModel : ApplicationModel = ApplicationModel ;
    private var date : Date = Date()
    private var restaurant : Restaurant = Restaurant(1, "El Tano", "inserte descripcion", "por quilmes oeste", geoLocation, mutableListOf<PaymentMethod>(), mutableSetOf<Product>());
    private  var client: Client = Client(1,"Roque saenz peña", date, geoLocation, "1212", applicationModel)


    private var order : Order = Order(2, client, restaurant, cash, menus)

    @Test
    fun addMenuAddsMenusInTheOrdersCollection() {
        Assert.assertEquals(0, menus.size)
        order.addMenu(menu0)
        Assert.assertEquals(1, menus.size)
        order.addMenu(menu1)
        Assert.assertEquals(2, menus.size)
        order.addMenu(menu2)
        Assert.assertEquals(3, menus.size)
        order.addMenu(menu3)
        Assert.assertEquals(4, menus.size)
    }

    @Test
    fun removeMenuRemovesMenusInTheOrdersCollection() {
        order.addMenu(menu0)
        order.addMenu(menu1)
        order.addMenu(menu2)
        order.addMenu(menu3)

        Assert.assertEquals(4, order.menus().size)
        order.removeMenu(menu3)
        Assert.assertEquals(3, order.menus().size)
        order.removeMenu(menu2)
        Assert.assertEquals(2, order.menus().size)
        order.removeMenu(menu1)
        Assert.assertEquals(1, order.menus().size)
        order.removeMenu(menu0)
        Assert.assertEquals(0, order.menus().size)
    }

    @Test
    fun theStateOfTheOrderIsDelivered() {
        order.delivered()
        Assert.assertEquals(StateOrder.DELIVERED,order.getState())
    }

    @Test
    fun theStateOfTheOrderIsDeliveredPending() {
        order.pending()
        Assert.assertEquals(StateOrder.PENDING,order.getState())
    }

    @Test
    fun theStateOfTheOrderIsDeliveredOnMyWay() {
        order.onMyWay()
        Assert.assertEquals(StateOrder.ONMYWAY,order.getState())
    }

    @Test
    fun theStateOfTheOrderIsDeliveredCancelled() {
        order.cancelled()
        Assert.assertEquals(StateOrder.CANCELLED,order.getState())
    }
}