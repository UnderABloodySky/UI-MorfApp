package restaurant

import applicationModel.MorfApp
import discount.Discount
import discount.FixedDiscount
import discount.NoDiscount
import geoclase.Geo
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
import user.Supervisor
import java.util.*

class TestRestaurant {
    private var geoLocation1 = Geo(1.2, 2.2)
    private var applicationModel = MorfApp
    private var cash = Cash()
    private var listOfPaymentMethod = mutableListOf<PaymentMethod>(cash)
    private var newRestaurant = applicationModel.createRestaurant("Asd", "asd", "asd", geoLocation1, listOfPaymentMethod)
    private var elClubDeLaMilanesa = applicationModel.createRestaurant("El club de la milanesa", "asd", "asd", geoLocation1, listOfPaymentMethod)
    private var otroAntro = applicationModel.createRestaurant("Asd", "las mejores milanesas", "asd", geoLocation1, listOfPaymentMethod)
    private var newSupervisor = Supervisor(1, "Pepe", "SuperPepe",newRestaurant, "123454")
    private var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf<Product>(), newRestaurant)
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.BEBIDA)

    @Before
    fun addSupervisor() {
        newRestaurant.addSupervisor(newSupervisor)
    }

    @Test
    fun testSearchOfRestaurantIsCorrect() {
        val criteria = CriteriaByString("milanesa")
        val res = applicationModel.findRestaurant(criteria)
        Assert.assertEquals(2, res.size)
        Assert.assertTrue(res.contains(elClubDeLaMilanesa))
        Assert.assertTrue(res.contains(otroAntro))
        Assert.assertFalse(res.contains(newRestaurant))

    }

    @Test
    fun testSearchInMenusOfTheRestaurantIsCorrect() {
        val criteria = CriteriaByString("milanesa")
        var one = newRestaurant.createMenu("Con Milanesa", "asd", mutableListOf(), newRestaurant, NoDiscount(), true)
        var two = newRestaurant.createMenu("asd2", "milanesa", mutableListOf(), newRestaurant, NoDiscount(), true)
        var three = elClubDeLaMilanesa.createMenu("Con Milanesa", "asd", mutableListOf(), elClubDeLaMilanesa, NoDiscount(), true)
        var four = elClubDeLaMilanesa.createMenu("asd", "con milanesa", mutableListOf(), elClubDeLaMilanesa, NoDiscount(), true)
        var five = otroAntro.createMenu("asd3", "asd", mutableListOf(), otroAntro, NoDiscount(), true)
        var six = otroAntro.createMenu("asd4", "asd", mutableListOf(), otroAntro, NoDiscount(), true)


        val res = applicationModel.findMenu(criteria)
        Assert.assertEquals(4, res.size)
        Assert.assertTrue(res.contains(one))
        Assert.assertTrue(res.contains(two))
        Assert.assertTrue(res.contains(three))
        Assert.assertTrue(res.contains(four))
        Assert.assertFalse(res.contains(five))
        Assert.assertFalse(res.contains(six))

    }

    @Test
    fun newRestaurantIsCreated() {
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        Assert.assertEquals("Asd", newRestaurant.name)
        Assert.assertEquals("asd",newRestaurant.description)
        Assert.assertEquals("asd", newRestaurant.direcction)
        Assert.assertEquals(geoLocation1, newRestaurant.geoLocation)
        Assert.assertEquals("SuperPepe", newRestaurant.supervisor.name)
    }

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
        val newSupervisor = newRestaurant.supervisor
        newSupervisor.addProductToRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.containsValue(soda))
        Assert.assertTrue(newRestaurant.products.containsKey(soda.code))
        newSupervisor.removeProductFromRestaurantStock(soda)
        Assert.assertTrue(newRestaurant.products.isEmpty())
    }

    @Test
    fun supervisorCanAddAAMenu() {
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        val newSupervisor = newRestaurant.supervisor
        newSupervisor.addMenuToRestaurant(menu)
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertTrue(newRestaurant.menuIsRegistered(menu))
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
        Assert.assertTrue(newRestaurant.menus.containsKey(menu.code))
    }

    @Test
    fun supervisorCanRemoveAMenu() {
        val newSupervisor = newRestaurant.supervisor
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
        val newSupervisor = newRestaurant.supervisor
        val debit = Debit("", 1, 1, Date())
        newSupervisor.addPaymentMethod(debit)
        Assert.assertTrue(newRestaurant.availablePaymentMethods.contains(cash))
        Assert.assertTrue(newRestaurant.availablePaymentMethods.contains(debit))
        newSupervisor.removePaymentMethodRestaurant(cash)
        Assert.assertEquals(mutableListOf(debit), newRestaurant.availablePaymentMethods)
        val emptyPaymentsMethods: MutableCollection<PaymentMethod> = mutableListOf<PaymentMethod>()
        newSupervisor.removePaymentMethodRestaurant(debit)
        Assert.assertEquals(emptyPaymentsMethods, newRestaurant.availablePaymentMethods )
    }

    @Test
    fun restaurantCanChangeItsSupervisor() {
        Assert.assertEquals(newSupervisor, newRestaurant.supervisor)
        val otherSupervisor = Supervisor(2, "Arya Stark", "TheWolfGirl", newRestaurant, "123454")
        newRestaurant.addSupervisor(otherSupervisor)
        Assert.assertFalse(newSupervisor == newRestaurant.supervisor)
        Assert.assertEquals(otherSupervisor, newRestaurant.supervisor)

    }

    @Test
    fun restaurantCanCreateItsOwnProductsAndAddItToItsStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        val product: Product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.PLATOPRINCIPAL)
        Assert.assertFalse(newRestaurant.products.isEmpty())
        Assert.assertEquals(1, newRestaurant.products.size)
        Assert.assertTrue(newRestaurant.products.containsValue(product))
    }

    @Test
    fun restaurantCanCreateItsOwnProductsAndItIsConsist() {
        val product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.PLATOPRINCIPAL)
        Assert.assertEquals("Arroz", product.name)
        Assert.assertEquals("directo de China", product.description)
        Assert.assertEquals(20.0, product.price, 0.0)
        Assert.assertEquals(Category.PLATOPRINCIPAL, product.category)
        Assert.assertEquals(0, product.code)
    }

    @Test
    fun restaurantCanEditItsOwnProducts() {
        val product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.PLATOPRINCIPAL)
        newRestaurant.editProduct(product.code, "Alloz", "directo de China", 20.0, Category.PLATOPRINCIPAL)
        Assert.assertEquals("Alloz", product.name)
        newRestaurant.editProduct(product.code, "Alloz", "directo de Japon", 20.0, Category.PLATOPRINCIPAL)
        Assert.assertEquals("directo de Japon", product.description)
        newRestaurant.editProduct(product.code, "Alloz", "directo de Japon", 30.0, Category.PLATOPRINCIPAL)
        Assert.assertEquals(30.0, product.price, 0.0)
        newRestaurant.editProduct(product.code, "Alloz", "directo de Japon", 30.0, Category.PLATOPRINCIPAL)
        Assert.assertEquals(Category.PLATOPRINCIPAL, product.category)
    }

    @Test
    fun restaurantDeleteAProductFromItsStock() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        val product: Product = newRestaurant.createProduct("Arroz", "directo de China", 20.0, Category.PLATOPRINCIPAL)
        Assert.assertFalse(newRestaurant.products.isEmpty())
        Assert.assertTrue(newRestaurant.products.containsValue(product))
        newRestaurant.deleteProduct(product.code)
        Assert.assertTrue(newRestaurant.products.isEmpty())
    }

    @Test
    fun restaurantCanCreateItsOwnMenusAndAddIt() {
        Assert.assertTrue(newRestaurant.menus.isEmpty())
        val menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        Assert.assertFalse(newRestaurant.menus.isEmpty())
        Assert.assertEquals(1, newRestaurant.menus.size)
        Assert.assertTrue(newRestaurant.menus.containsValue(menu))
    }

    @Test
    fun restaurantCanCreateItsOwnMenuAndItIsConsist() {
        val discount : Discount = NoDiscount()
        val menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, discount, false)
        Assert.assertEquals("MenuFantasma", menu.name)
        Assert.assertEquals("Buuu", menu.description)
        Assert.assertFalse(menu.enabled)
        Assert.assertEquals(discount, menu.discount)
        Assert.assertEquals(0, menu.code)
        Assert.assertEquals(mutableListOf<Product>(), menu.productsOfMenu)
    }

    @Test
    fun restaurantCanEditItsOwnMenusAndItIsConsist() {
        val menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Buuu", mutableListOf(), NoDiscount(), false)
        Assert.assertEquals("NuevoNombre", menu.name)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Otra cosa", mutableListOf(), NoDiscount(), false)
        Assert.assertEquals("Otra cosa", menu.description)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Algo", mutableListOf(soda), NoDiscount(), false)
        Assert.assertTrue(menu.productsOfMenu.contains(soda))
        val discount = FixedDiscount(48.0)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Algo", mutableListOf(soda), discount, false)
        Assert.assertEquals(discount, menu.discount)
        newRestaurant.editMenu(menu.code, "NuevoNombre", "Algo", mutableListOf(soda), NoDiscount(), true)
        Assert.assertTrue(menu.enabled)
    }

    @Test
    fun restaurantDeleteAMenu() {
        Assert.assertTrue(newRestaurant.products.isEmpty())
        val menu: Menu = newRestaurant.createMenu("MenuFantasma", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
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
        newRestaurant.removePaymentMethod(cash)
        Assert.assertTrue(newRestaurant.availablePaymentMethods.isEmpty())
        newRestaurant.addPaymentMethod(Debit("", 123, 1, Date()))
        Assert.assertFalse(newRestaurant.availablePaymentMethods.isEmpty())
    }

    @Test
    fun restaurantCanRemoveANewPaymentMethod(){
        newRestaurant.removePaymentMethod(cash)
        Assert.assertTrue(newRestaurant.availablePaymentMethods.isEmpty())
        val debit : PaymentMethod = Debit("", 123, 1, Date())
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
        val list = newRestaurant.menusAvailable() as MutableMap<Int,Menu>
        Assert.assertTrue(list.isEmpty())
    }

    @Test
    fun restaurantWithEnabledMenusReturnTheCorrectCollection() {
        val res : Map<Int, Menu> = newRestaurant.menusAvailable()
        val menu0: Menu = newRestaurant.createMenu("MenuFantasma0", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        val menu1: Menu = newRestaurant.createMenu("MenuFantasma1", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        val menu2: Menu = newRestaurant.createMenu("MenuFantasma2", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        val menu3: Menu = newRestaurant.createMenu("MenuFantasma3", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        val menu4: Menu = newRestaurant.createMenu("MenuFantasma4", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)

        Assert.assertFalse(res.containsValue(menu4))
        Assert.assertFalse(res.containsValue(menu3))
        Assert.assertFalse(res.containsValue(menu2))
        Assert.assertFalse(res.containsValue(menu1))
        Assert.assertFalse(res.containsValue(menu0))
    }


    @Test
    fun restaurantReturnsTheCorrectMenuesByString(){
        val menu0: Menu = newRestaurant.createMenu("MenuFantasma0", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), false)
        val menu7: Menu = newRestaurant.createMenu("MenuFantasma1", "Buuu", mutableListOf(), newRestaurant, NoDiscount(), true)

        val criteriaByString = CriteriaByString("MenuFantasma")
        val menues = newRestaurant.findMenuesPair(criteriaByString).first
        val restaurant = newRestaurant.findMenuesPair(criteriaByString).second

        Assert.assertTrue(menues.contains(menu0))
        Assert.assertTrue(menues.contains(menu7))
        Assert.assertEquals(2, menues.size)
        Assert.assertEquals(newRestaurant, restaurant)

    }

}