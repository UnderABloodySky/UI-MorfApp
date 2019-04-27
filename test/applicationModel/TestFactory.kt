package applicationModel

import discount.FixedDiscount
import geoclaseui.Geo
import order.Order
import org.junit.Assert
import org.junit.Test
import paymentMethod.Cash
import paymentMethod.PaymentMethod
import productAndMenu.Category
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant
import user.Client
import user.Supervisor
import java.util.*

class TestFactory{

    private var orderFactory : OrderFactory = OrderFactory()
    private var clientFactory : ClientFactory = ClientFactory()
    private var restaurantFactory : RestaurantFactory = RestaurantFactory()
    private var productFactory : ProductFactory = ProductFactory()
    private var menuFactory : MenuFactory = MenuFactory()

    private var applicationModel : MorfApp = MorfApp ;
    private val cash : PaymentMethod = Cash()
    private val listOfPaymentMethod : MutableCollection<PaymentMethod> = mutableListOf(cash)
    private var geoLocation: Geo = Geo(1.2,2.2);
    private var iceCream = Product(1, "Soda", "Made with milk from happy cows", 20.0, Category.DISSERT);
    private var pizza = Product(2, "HotDog", "Really italian pepperoni pizza", 40.0, Category.MAINDISH);
    private var restaurant : Restaurant = Restaurant(1, "El Tano", "inserte descripcion", "por quilmes oeste", geoLocation, mutableListOf(cash));
    private var menu0 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),restaurant);
    private var menu1 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),restaurant);
    private var menu2 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),restaurant);
    private var menu3 = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),restaurant);
    private var menu4 = Menu(2, "SweetMenu", "Muuuuuuu", mutableListOf<Product>(iceCream), restaurant)
    private var menu5 = Menu(2, "SaltyMenu", "Pizza Time", mutableListOf<Product>(pizza), restaurant)
    private var menu6 = Menu(2, "FullyMenu", "Good friends, good FOOD, good times", mutableListOf<Product>(iceCream, pizza), restaurant)
    private val menus : MutableCollection<Menu> = mutableListOf<Menu>(menu0,menu1,menu2,menu3,menu4,menu5,menu6)
    private var date : Date = Date()
    private  var client: Client = Client(1,"Pepe","Roque saenz peña", date, geoLocation, "1212", applicationModel)
    private var products : MutableCollection<Product> = mutableListOf(iceCream, pizza)

    //OrderFactory
    @Test
    fun testOrderFactoryHasIniciallyTheCounterInOne(){
        Assert.assertEquals(0,orderFactory.code())
    }

    @Test
    fun theOrderFactoryIncreasesItsCodeByOneWhenItCreatesAnOrder(){
        orderFactory.createOrder(client, restaurant, cash, menus)
        Assert.assertEquals(1,orderFactory.code())
        orderFactory.createOrder(client, restaurant, cash, menus)
        Assert.assertEquals(2,orderFactory.code())
    }

    @Test
    fun testOrderFactoryReturnsAConsistentOrder(){
        var newOrder : Order = orderFactory.createOrder(client, restaurant, cash, menus)
        Assert.assertEquals(menus, newOrder.menus())
        Assert.assertEquals(0, newOrder.code)
        Assert.assertEquals(client, newOrder.getUser())
        Assert.assertEquals(restaurant, newOrder.getRestaurant())
        Assert.assertEquals(cash, newOrder.getPaymentMethod())
    }

    //ClientFactory
    @Test
    fun testClientFactoryHasIniciallyTheCounterInZero(){
        Assert.assertEquals(0,clientFactory.code())
    }

    @Test
    fun theClientFactoryIncreasesItsCodeByOneWhenItCreatesAnOrder(){
        Assert.assertEquals(0,clientFactory.code())
        clientFactory.createClient("Algun lado", date, geoLocation, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals(1,clientFactory.code())
        clientFactory.createClient("Algun lado", date, geoLocation, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals(2,clientFactory.code())
    }

    @Test
    fun testClientFactoryReturnsAConsistentClient(){
        var newClient : Client = clientFactory.createClient("Algun lado", date, geoLocation, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals("Algun lado",newClient.address)
        Assert.assertEquals(0, newClient.code)
        Assert.assertEquals(date, newClient.registrationDate)
        Assert.assertEquals(geoLocation, newClient.geoLocation)
        Assert.assertEquals("Pepe", newClient.name)
        Assert.assertEquals("Aloha", newClient.password)
        Assert.assertEquals(applicationModel, newClient.applicationModel)
    }

    @Test
    fun testClientFactoryReturnsAConsistentSupervisor(){
        var supervisor : Supervisor = clientFactory.createSupervisor(restaurant, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals(0, supervisor.code)
        Assert.assertEquals("Pepe", supervisor.name)
        Assert.assertEquals("Aloha", supervisor.password)
        Assert.assertEquals(applicationModel, supervisor.applicationModel)
    }

    @Test
    fun theClientFactoryIncreasesItsCounterWhenItCreatesAnClientOrASupervisor(){
        Assert.assertEquals(0,orderFactory.code())
        clientFactory.createClient("Algun lado", date, geoLocation, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals(1,clientFactory.code())
        clientFactory.createClient("Algun lado", date, geoLocation, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals(2,clientFactory.code())
        clientFactory.createSupervisor(restaurant, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals(3,clientFactory.code())
        clientFactory.createSupervisor(restaurant, "Pepe", "Aloha", applicationModel)
        Assert.assertEquals(4,clientFactory.code())
    }

    //RestaurantFactory
    @Test
    fun testRestaurantFactoryHasIniciallyTheCounterInZero(){
        Assert.assertEquals(0,restaurantFactory.code())
    }

    @Test
    fun theRestaurantFactoryIncreasesItsCodeByOneWhenItCreatesARestaurant(){
        Assert.assertEquals(0,restaurantFactory.code())
        restaurantFactory.createRestaurant("Los Maizales A", "ATR", "Por Caballito", geoLocation, listOfPaymentMethod)
        Assert.assertEquals(1,restaurantFactory.code())
        restaurantFactory.createRestaurant("Los Maizales B", "ATR", "Por Caballito", geoLocation, listOfPaymentMethod)
        Assert.assertEquals(2,restaurantFactory.code())
    }

    @Test
    fun testRestaurantFactoryReturnsAConsistentRestaurant(){
        var newRestaurant : Restaurant = restaurantFactory.createRestaurant("Los Maizales A", "ATR", "Por Caballito", geoLocation, listOfPaymentMethod)
        Assert.assertEquals("Por Caballito", newRestaurant.direcction)
        Assert.assertEquals("Los Maizales A", newRestaurant.name)
        Assert.assertEquals(geoLocation, newRestaurant.geoLocation)
        Assert.assertEquals("ATR", newRestaurant.description)
        Assert.assertEquals(mutableListOf(cash), newRestaurant.availablePaymentMethods)
    }

    //ProductFactory
    @Test
    fun testProductFactoryHasIniciallyTheCounterInZero(){
        Assert.assertEquals(0,productFactory.code())
    }

    @Test
    fun theProductFactoryIncreasesItsCodeByOneWhenItCreatesAProduct(){
        productFactory.createProduct("Asd", "A crazy description", 0.0, Category.DRINK)
        Assert.assertEquals(1,productFactory.code())
        productFactory.createProduct("Capitan del Espacio", "A crazy description", 30.0, Category.DRINK)
        Assert.assertEquals(2,productFactory.code())
    }

    @Test
    fun testProductFactoryReturnsAConsistentProduct(){
        var newProduct : Product = productFactory.createProduct("Capitan del Espacio", "A crazy description", 30.0, Category.DISSERT)
        Assert.assertTrue(30.0 == newProduct.price)
        Assert.assertEquals(Category.DISSERT, newProduct.category)
        Assert.assertEquals("Capitan del Espacio", newProduct.name)
        Assert.assertEquals("A crazy description", newProduct.description)
    }

    //MenuFactory
    @Test
    fun testMenuFactoryHasIniciallyTheCounterInZero(){
        Assert.assertEquals(0,menuFactory.code())
    }

    @Test
    fun theMenuFactoryIncreasesItsCodeByOneWhenItCreatesAMenu(){
        menuFactory.createMenu("AsdMenu", "A crazy description", products, restaurant, FixedDiscount(40.0), false)
        Assert.assertEquals(1,menuFactory.code())
        menuFactory.createMenu("AsdMenu", "A crazy description", products, restaurant, FixedDiscount(40.0), false)
        Assert.assertEquals(2,menuFactory.code())
    }

    @Test
    fun testMenuFactoryReturnsAConsistentMenu(){
        var discount : FixedDiscount = FixedDiscount(40.0)
        var newMenu : Menu =  menuFactory.createMenu("AsdMenu", "A crazy description", products, restaurant, discount, false)
        Assert.assertFalse(newMenu.enabled)
        Assert.assertEquals("A crazy description", newMenu.description)
        Assert.assertEquals(restaurant, newMenu.restaurant)
        Assert.assertEquals(discount, newMenu.discount)
        Assert.assertEquals("AsdMenu", newMenu.name)
    }

}