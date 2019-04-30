package restaurant

import applicationModel.MorfApp
import discount.Discount
import discount.FixedDiscount
import discount.NoDiscount
import geoclaseui.Geo
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import paymentMethod.Cash
import paymentMethod.Debit
import paymentMethod.PaymentMethod
import productAndMenu.Category
import productAndMenu.Menu
import productAndMenu.Product
import searcher.CriteriaByString
import searcher.Searcher
import user.Supervisor
import java.util.*

class TestRestaurant {
    private var geoLocation1: Geo = Geo(1.2, 2.2)
    private var applicationModel: MorfApp = MorfApp;
    private var cash: PaymentMethod = Cash()
    private var listOfPaymentMethod: MutableCollection<PaymentMethod> = mutableListOf(cash)
    var newRestaurant: Restaurant = Restaurant(1, "El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1, mutableListOf())
    private var newSupervisor: Supervisor = Supervisor(1, "SuperPepe", newRestaurant, "123454", applicationModel)
    private var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf<Product>(), newRestaurant)
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK)

    @Before
    fun addSupervisor() {
        newRestaurant.addSupervisor(newSupervisor)
    }

    @Test
    fun newRestaurantIsCreated() {
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        Assert.assertEquals(newRestaurant.code, 1)
        Assert.assertEquals(newRestaurant.name, "El Tano")
        Assert.assertEquals(newRestaurant.description, "inserte descripcion")
        Assert.assertEquals(newRestaurant.direcction, "por quilmes oeste")
        Assert.assertEquals(newRestaurant.geoLocation, geoLocation1)
        Assert.assertEquals("SuperPepe", newRestaurant.supervisor.name)
    }

    //Supervisor
    @Test
    fun supervisorAddProductsInStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        newRestaurant.addSupervisor(newSupervisor)
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
    }

    @Test
    fun supervisorCanRemovePrductsFromStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        var newSupervisor: Supervisor = newRestaurant.supervisor
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
        Assert.assertTrue(newRestaurant.products.containsKey(soda.code))
        newSupervisor.removeProductFromRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.isEmpty())
    }

    @Test
    fun supervisorCanAddAAMenu() {
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        var newSupervisor: Supervisor = newRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
        Assert.assertTrue(newRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun supervisorCanRemoveAMenu() {
        var newSupervisor: Supervisor = newRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        newSupervisor.removeMenuFromRestaurant(menu)
        Assert.assertFalse(newRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newRestaurant.menus.containsValue(menu))
        Assert.assertFalse(newRestaurant.menus.containsKey(menu.code))
        Assert.assertTrue(newRestaurant.menus.isEmpty())
    }

    @Test
    fun supervisorCanAddAndPaymentMethodsInTheRestaurant() {
        var cash: PaymentMethod = Cash()
        newRestaurant.addSupervisor(newSupervisor)
        newSupervisor.addPaymentMethod(cash);
        Assert.assertTrue(newRestaurant.availablePaymentMethods.contains(cash))
        newSupervisor.removePaymentMethodRestaurant(cash)
        var emptyPaymentsMethods: MutableCollection<PaymentMethod> = mutableListOf<PaymentMethod>()
        Assert.assertEquals(newRestaurant.availablePaymentMethods, emptyPaymentsMethods)
    }

    @Test
    fun restaurantCanChangeItsSupervisor() {
        Assert.assertEquals(newSupervisor, newRestaurant.supervisor)
        var otherSupervisor: Supervisor = Supervisor(2, "Arya Stark", newRestaurant, "123454", applicationModel)
        newRestaurant.addSupervisor(otherSupervisor)
        Assert.assertFalse(newSupervisor == newRestaurant.supervisor)
        Assert.assertEquals(otherSupervisor, newRestaurant.supervisor)

    }

    //Product
    @Test
    fun restaurantCanCreateItsOwnProductsAndAddItToItsStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        var product: Product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.MAINDISH)
        Assert.assertFalse(newRestaurant.products.isEmpty())
        Assert.assertEquals(1, newRestaurant.products.size)
        Assert.assertTrue(newRestaurant.products.containsValue(product))
    }

    @Test
    fun restaurantCanCreateItsOwnProductsAndItIsConsist() {
        var product: Product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.MAINDISH)
        Assert.assertEquals("Arroz", product.name)
        Assert.assertEquals("directo de China", product.description)
        Assert.assertEquals(20.0, product.price, 0.0)
        Assert.assertEquals(Category.MAINDISH, product.category)
        Assert.assertEquals(0, product.code)
    }

    @Test
    fun restaurantCanEditItsOwnProducts() {
        var product: Product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.MAINDISH)
        newRestaurant.editProduct(product.code, "Alloz", "directo de China", 20.0, Category.MAINDISH)
        Assert.assertEquals("Alloz", product.name)
        newRestaurant.editProduct(product.code, "Alloz", "directo de Japon", 20.0, Category.MAINDISH)
        Assert.assertEquals("directo de Japon", product.description)
        newRestaurant.editProduct(product.code, "Alloz", "directo de Japon", 30.0, Category.MAINDISH)
        Assert.assertEquals(30.0, product.price, 0.0)
        newRestaurant.editProduct(product.code, "Alloz", "directo de Japon", 30.0, Category.NONE)
        Assert.assertEquals(Category.NONE, product.category)
    }

    @Test
    fun restaurantDeleteAProductFromItsStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        var product: Product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.MAINDISH)
        Assert.assertFalse(newRestaurant.products.isEmpty())
        Assert.assertTrue(newRestaurant.products.containsValue(product))
        newRestaurant.deleteProduct(product.code)
        Assert.assertTrue(newRestaurant.products.isEmpty())
    }


    //Menu
    @Test
    fun restaurantCanCreateItsOwnMenusAndAddIt() {
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        var menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertEquals(1, newRestaurant.menus.size)
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
    }

    @Test
    fun restaurantCanCreateItsOwnMenuAndItIsConsist() {
        var discount : Discount = NoDiscount()
        var menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, discount, false)
        Assert.assertEquals("MenuFantasma", menu.name)
        Assert.assertEquals("Buuu", menu.description)
        Assert.assertFalse(menu.enabled)
        Assert.assertEquals(discount, menu.discount)
        Assert.assertEquals(0, menu.code)
        Assert.assertEquals(mutableListOf<Product>(), menu.productsOfMenu)
    }

    @Test
    fun restaurantCanEditItsOwnMenusAndItIsConsist() {
        var menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Buuu", mutableListOf(), NoDiscount(), false)
        Assert.assertEquals("NuevoNombre", menu.name)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Otra cosa", mutableListOf(), NoDiscount(), false)
        Assert.assertEquals("Otra cosa", menu.description)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Algo", mutableListOf(soda), NoDiscount(), false)
        Assert.assertTrue(menu.productsOfMenu.contains(soda))
        var discount : FixedDiscount = FixedDiscount(48.0)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Algo", mutableListOf(soda), discount, false)
        Assert.assertEquals(discount, menu.discount)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Algo", mutableListOf(soda), NoDiscount(), true)
        Assert.assertTrue(menu.enabled)
    }

    @Test
    fun restaurantDeleteAMenu() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        var menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
        newRestaurant.deleteMenu(menu.code)
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        Assert.assertFalse(newRestaurant.menus.containsValue(menu))
        Assert.assertFalse(newRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun restaurantAddProductsInItsStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        newRestaurant.addProductToStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
        Assert.assertTrue(newRestaurant.products.containsKey(soda.code))
        Assert.assertFalse(newRestaurant.products.isEmpty())
    }


    @Test
    fun restaurantRemoveProductsInItsStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        newRestaurant.addProductToStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
        Assert.assertTrue(newRestaurant.products.containsKey(soda.code))
        Assert.assertFalse(newRestaurant.products.isEmpty())
        newRestaurant.removeProductFromStock(soda)
        Assert.assertTrue(newRestaurant.products.isEmpty())
    }


    @Test
    fun restaurantContainsASodaInItsStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        newRestaurant.addProductToStock(soda)
        Assert.assertTrue(newRestaurant.productIsContainedInTheStock(soda))
    }


    @Test
    fun restaurantNoContainsASodaInItsStock() {
        Assert.assertFalse(newRestaurant.productIsContainedInTheStock(soda))
    }

    @Test
    fun restaurantCanAddANewPaymentMethod(){
        Assert.assertTrue(newRestaurant.availablePaymentMethods.isEmpty())
        newRestaurant.addPaymentMethod(Debit())
        Assert.assertFalse(newRestaurant.availablePaymentMethods.isEmpty())
    }

    @Test
    fun restaurantCanRemoveANewPaymentMethod(){
        Assert.assertTrue(newRestaurant.availablePaymentMethods.isEmpty())
        var debit : PaymentMethod = Debit()
        newRestaurant.addPaymentMethod(debit)
        Assert.assertFalse(newRestaurant.availablePaymentMethods.isEmpty())
        newRestaurant.removePaymentMethod(debit)
        Assert.assertTrue(newRestaurant.availablePaymentMethods.isEmpty())
    }

    @Test
    fun restaurantCanAddANewMenu(){
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        newRestaurant.addMenu(menu)
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
        Assert.assertTrue(newRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun restaurantCanRemoveANewMenu(){
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        newRestaurant.addMenu(menu)
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
        Assert.assertTrue(newRestaurant.menus.containsKey(menu.code))
        newRestaurant.removeMenu(menu)
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        Assert.assertFalse(newRestaurant.menus.containsValue(menu))
        Assert.assertFalse(newRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun restaurantKnowIfAMenuIsNoRegistered(){
        Assert.assertFalse(newRestaurant.menuIsRegistered(menu))
    }

    @Test
    fun restaurantKnowIfAMenuIsRegistered(){
        newRestaurant.addMenu(menu)
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
    }

    @Test
    fun restaurantWithoutEnabledMenusReturnAEmptyCollection(){
        var list : MutableMap<Int, Menu> = newRestaurant.menusAvailable() as MutableMap<Int,Menu>
        Assert.assertTrue(list.isEmpty())
    }

    @Test
    fun restaurantWithEnabledMenusReturnTheCorrectCollection() {
        var res : Map<Int, Menu> = newRestaurant.menusAvailable()
        var menu0: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu1: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu2: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu3: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu4: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu5: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu6: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu7: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu8: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)

//      Assert.assertTrue(res.containsKey(menu8.code))
//      Assert.assertTrue(res.containsValue(menu7))
//      Assert.assertTrue(res.containsValue(menu6))
        Assert.assertFalse(res.containsValue(menu4))
        Assert.assertFalse(res.containsValue(menu3))
        Assert.assertFalse(res.containsValue(menu2))
        Assert.assertFalse(res.containsValue(menu1))
        Assert.assertFalse(res.containsValue(menu0))
    }


    @Test
    fun restaurantReturnsTheCorrectMenuesByString(){
        var menu0: Menu = newRestaurant.createMenu("MenuFantasma0", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu1: Menu = newRestaurant.createMenu("xxx", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu2: Menu = newRestaurant.createMenu("yyy", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu3: Menu = newRestaurant.createMenu("zzz", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        var menu4: Menu = newRestaurant.createMenu("yyy", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu5: Menu = newRestaurant.createMenu("aaa", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu6: Menu = newRestaurant.createMenu("bbb", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu7: Menu = newRestaurant.createMenu("MenuFantasma1", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)
        var menu8: Menu = newRestaurant.createMenu("ddd", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)

        var criteriaByString : CriteriaByString = CriteriaByString("MenuFantasma")
        var menues = newRestaurant.findMenuesPair(criteriaByString).first
        var restaurant = newRestaurant.findMenuesPair(criteriaByString).second

        Assert.assertTrue(menues.contains(menu0))
        Assert.assertTrue(menues.contains(menu7))
        Assert.assertEquals(2, menues.size)
        Assert.assertEquals(newRestaurant, restaurant)

    }

}