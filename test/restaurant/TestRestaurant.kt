package restaurant

import applicationModel.MorfApp
import geoclaseui.Geo
import org.junit.Assert
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
    private var newRestaurant: Restaurant = Restaurant(1,"El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1)
    private var newSupervisor : Supervisor = Supervisor(1,"SuperPepe", newRestaurant, "123454",applicationModel)
    private var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(), newRestaurant)
    private var emptyProductList: MutableSet<Product> = mutableSetOf<Product>()
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK)

    @Test
    fun newRestaurantIsCreated(){
        var newTestRestaurant: Restaurant = Restaurant(1,"El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1)
        Assert.assertTrue(newTestRestaurant.menus.isEmpty())
        Assert.assertEquals(newTestRestaurant.code,1)
        Assert.assertEquals(newTestRestaurant.name,"El Tano")
        Assert.assertEquals(newTestRestaurant.description,"inserte descripcion")
        Assert.assertEquals(newTestRestaurant.direcction,"por quilmes oeste")
        Assert.assertEquals(newTestRestaurant.geoLocation,geoLocation1)
        Assert.assertEquals("RootElTano1",newTestRestaurant.supervisor.name)
    }

    @Test
    fun testPrueba(){
        var example : String =  "Esto Es Un Ejemplo"
        Assert.assertEquals("EstoEsUnEjemplo",example.replace(" ", ""))
    }


    @Test
    fun supervisorAddProductsInStock(){
        Assert.assertTrue(newRestaurant.products.isEmpty())
        newRestaurant.addSupervisor(newSupervisor)
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
    }

    @Test
    fun supervisorCanRemovePrductsFromStock(){
        Assert.assertTrue(newRestaurant.products.isEmpty())
        var newSupervisor : Supervisor = newRestaurant.supervisor
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
        Assert.assertTrue(newRestaurant.products.containsKey(soda.code))
        newSupervisor.removeProductFromRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.isEmpty())
    }

    @Test
    fun supervisorCanAddAAMenu(){
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        var newSupervisor : Supervisor = newRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
        Assert.assertTrue(newRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun supervisorCanRemoveAMenu(){
        var newSupervisor : Supervisor = newRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        newSupervisor.removeMenuFromRestaurant(menu)
        Assert.assertFalse(newRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newRestaurant.menus.containsValue(menu))
        Assert.assertFalse(newRestaurant.menus.containsKey(menu.code))
        Assert.assertTrue(newRestaurant.menus.isEmpty())
    }

    @Test
    fun supervisorCanAddAndPaymentMethodsInTheRestaurant(){
        var cash:PaymentMethod= Cash()
        newRestaurant.addSupervisor(newSupervisor)
        newSupervisor.addPaymentMethod(cash);
        Assert.assertTrue(newRestaurant.availablePaymentMethods.contains(cash))
        newSupervisor.removePaymentMethodRestaurant(cash)
        var emptyPaymentsMethods : MutableCollection<PaymentMethod> = mutableListOf<PaymentMethod>()
        Assert.assertEquals(newRestaurant.availablePaymentMethods,emptyPaymentsMethods)
    }
}