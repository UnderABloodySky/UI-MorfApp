package restaurant

import applicationModel.MorfApp
import geoclaseui.Geo
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import paymentMethod.Cash
import paymentMethod.PaymentMethod
import productAndMenu.Category
import productAndMenu.Menu
import productAndMenu.Product
import user.Supervisor
import java.util.*

class TestRestaurant {
    private var geoLocation1: Geo = Geo(1.2,2.2)
    private var applicationModel : MorfApp = MorfApp ;
    private var cash : PaymentMethod = Cash()
    private var listOfPaymentMethod : MutableCollection<PaymentMethod> = mutableListOf(cash)
    var newTestRestaurant: Restaurant = Restaurant(1,"El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1,mutableListOf())
    private var newSupervisor : Supervisor = Supervisor(1,"SuperPepe", newTestRestaurant, "123454",applicationModel)
    private var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(), newTestRestaurant)
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK)

    @Before
    fun addSupervisor(){
        newTestRestaurant.addSupervisor(newSupervisor)
    }

    @Test
    fun newRestaurantIsCreated(){
        Assert.assertTrue(newTestRestaurant.menus.isEmpty())
        Assert.assertEquals(newTestRestaurant.code,1)
        Assert.assertEquals(newTestRestaurant.name,"El Tano")
        Assert.assertEquals(newTestRestaurant.description,"inserte descripcion")
        Assert.assertEquals(newTestRestaurant.direcction,"por quilmes oeste")
        Assert.assertEquals(newTestRestaurant.geoLocation,geoLocation1)
        Assert.assertEquals("SuperPepe",newTestRestaurant.supervisor.name)
    }

    @Test
    fun supervisorAddProductsInStock(){
        Assert.assertTrue(newTestRestaurant.products.isEmpty())
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newTestRestaurant.products.containsValue(soda))
    }

    @Test
    fun supervisorCanRemovePrductsFromStock(){
        Assert.assertTrue(newTestRestaurant.products.isEmpty())
        var newSupervisor : Supervisor = newTestRestaurant.supervisor
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newTestRestaurant.products.containsValue(soda))
        Assert.assertTrue(newTestRestaurant.products.containsKey(soda.code))
        newSupervisor.removeProductFromRestaurantStock(soda)
        Assert.assertTrue(newTestRestaurant.products.isEmpty())
    }

    @Test
    fun supervisorCanAddAAMenu(){
        Assert.assertTrue(newTestRestaurant.menus.isEmpty())
        var newSupervisor : Supervisor = newTestRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertTrue(newTestRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newTestRestaurant.menus.isEmpty())
        Assert.assertTrue(newTestRestaurant.menuIsRegistered(menu))
        Assert.assertTrue(newTestRestaurant.menus.containsValue(menu))
        Assert.assertTrue(newTestRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun supervisorCanRemoveAMenu(){
        var newSupervisor : Supervisor = newTestRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertFalse(newTestRestaurant.menus.isEmpty())
        newSupervisor.removeMenuFromRestaurant(menu)
        Assert.assertFalse(newTestRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newTestRestaurant.menus.containsValue(menu))
        Assert.assertFalse(newTestRestaurant.menus.containsKey(menu.code))
        Assert.assertTrue(newTestRestaurant.menus.isEmpty())
    }

    @Test
    fun supervisorCanAddPaymentMethodsInTheRestaurant(){
        Assert.assertTrue(newTestRestaurant.availablePaymentMethods.isEmpty())
        var cash:PaymentMethod= Cash()
        newSupervisor.addPaymentMethod(cash);
        Assert.assertTrue(newTestRestaurant.availablePaymentMethods.contains(cash))
        Assert.assertEquals(1,newTestRestaurant.availablePaymentMethods.size)

    }

    @Test
    fun supervisorCanRemovePaymentMethodsInTheRestaurant(){
        Assert.assertTrue(newTestRestaurant.availablePaymentMethods.isEmpty())
        var cash:PaymentMethod= Cash()
        newSupervisor.addPaymentMethod(cash);
        Assert.assertTrue(newTestRestaurant.availablePaymentMethods.contains(cash))
        newSupervisor.removePaymentMethodRestaurant(cash)
        Assert.assertTrue(newTestRestaurant.availablePaymentMethods.isEmpty())
    }
}