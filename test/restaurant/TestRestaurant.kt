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

private class TestRestaurant {

    private var geoLocation1: Geo = Geo(1.2,2.2);
    private var applicationModel : MorfApp = MorfApp ;
    private var date = Date()
    private var newRestaurant: Restaurant = Restaurant(1,"El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1, applicationModel);
    private var newSupervisor : Supervisor = Supervisor(1,"SuperPepe", newRestaurant, "123454", applicationModel)
    private var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(), newRestaurant);
    private var emptyMenuList: MutableSet<Menu> = mutableSetOf<Menu>();
    private var emptyProductList: MutableSet<Product> = mutableSetOf<Product>();
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK);

    @Test
    fun newRestaurantIsCreated(){
        var emptyMenuList: MutableSet<Menu> = mutableSetOf<Menu>();
        var emptyProductList: MutableSet<Product> = mutableSetOf<Product>();
        var newTestRestaurant: Restaurant = Restaurant(1,"El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1, applicationModel);
        Assert.assertEquals(newTestRestaurant.menus,emptyMenuList);
        Assert.assertEquals(newTestRestaurant.products,emptyProductList);
        Assert.assertEquals(newTestRestaurant.code,1);
        Assert.assertEquals(newTestRestaurant.name,"El Tano");
        Assert.assertEquals(newTestRestaurant.description,"inserte descripcion");
        Assert.assertEquals(newTestRestaurant.direcction,"por quilmes oeste");
        Assert.assertEquals(newTestRestaurant.geoLocation,geoLocation1);
    }

    @Test
    fun supervisorAddProductsInStock(){
        Assert.assertEquals(newRestaurant.products,emptyProductList);
        newRestaurant.addSupervisor(newSupervisor);
        newSupervisor.addProductToRestaurantStock(soda);
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
    }

    @Test
    fun supervisorCanRemovePrductsFromStock(){
        newRestaurant.addSupervisor(newSupervisor);
        newSupervisor.addProductToRestaurantStock(soda);
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
        newSupervisor.removeProductFromRestaurantStock(soda);
        Assert.assertEquals(newRestaurant.products,emptyProductList);
    }

    @Test
    fun supervisorCanAddAndRemoveAMenuToTheRestaurant(){
        newRestaurant.addSupervisor(newSupervisor);
        newSupervisor.addMenuToRestaurant(menu);
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        newSupervisor.removeMenuFromRestaurant(menu);
        Assert.assertEquals(newRestaurant.menus,emptyMenuList);
    }

    @Test
    fun supervisorCanAddAndRemoveAMenu(){
        newRestaurant.addSupervisor(newSupervisor);
        newSupervisor.addMenuToRestaurant(menu);
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        newSupervisor.removeMenuFromRestaurant(menu);
        Assert.assertEquals(newRestaurant.menus,emptyMenuList);
    }

    @Test
    fun supervisorCanAddAndPaymentMethodsInTheRestaurant(){
        var cash:PaymentMethod= Cash()
        newRestaurant.addSupervisor(newSupervisor);
        newSupervisor.addPaymentMethod(cash);
        Assert.assertEquals(newRestaurant.availablePaymentMethods,cash)
        newSupervisor.removePaymentMethodRestaurant(cash);
        var emptyPaymentsMethods : MutableCollection<PaymentMethod> = mutableListOf<PaymentMethod>()
        Assert.assertEquals(newRestaurant.availablePaymentMethods,emptyPaymentsMethods);
    }
}