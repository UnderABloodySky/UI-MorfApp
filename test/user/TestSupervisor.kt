package user

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
import restaurant.Restaurant

class TestSupervisor {

    private var geoLocation: Geo = Geo(48.84,84.48)
    private var applicationModel : MorfApp = MorfApp
    private var newRestaurant: Restaurant = applicationModel.createRestaurant("El Tano", "Una descripcion bien chingona", "Por Quilmes Oeste", geoLocation, mutableListOf())

    private var newSupervisor : Supervisor = Supervisor(1,"Pepe","SuperPepe", newRestaurant, "123454", applicationModel)
    private var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(), newRestaurant)
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.BEBIDA)

    @Before
    fun addSupervisor(){
        newRestaurant.addSupervisor(newSupervisor)
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
        val newSupervisor : Supervisor = newRestaurant.supervisor
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
        Assert.assertTrue(newRestaurant.products.containsKey(soda.code))
        newSupervisor.removeProductFromRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.isEmpty())
    }

    @Test
    fun supervisorCanAddAAMenu(){
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        val newSupervisor : Supervisor = newRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
        Assert.assertTrue(newRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun supervisorCanRemoveAMenu(){
        val newSupervisor : Supervisor = newRestaurant.supervisor
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
        val cash:PaymentMethod= Cash()
        newRestaurant.addSupervisor(newSupervisor)
        newSupervisor.addPaymentMethod(cash)
        Assert.assertTrue(newRestaurant.availablePaymentMethods.contains(cash))
        newSupervisor.removePaymentMethodRestaurant(cash)
        val emptyPaymentsMethods : MutableCollection<PaymentMethod> = mutableListOf()
        Assert.assertEquals(newRestaurant.availablePaymentMethods,emptyPaymentsMethods)
    }

}