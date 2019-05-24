package searcher

import geoclase.Geo
import org.junit.Assert
import org.junit.Test
import paymentMethod.Cash
import paymentMethod.Debit
import paymentMethod.PaymentMethod
import productAndMenu.Category
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant

class TestSearcher {

    private val searcher: Searcher = Searcher()

    private val geoLocation: Geo = Geo(2.0, 1.0)

    private val cash : PaymentMethod  = Cash()
    private val debit : PaymentMethod = Debit()
    private val paymentMethods : MutableCollection<PaymentMethod> = mutableListOf(cash, debit)
    private val restaurant0: Restaurant = Restaurant(0, "El Tano", "LLenadero magico de tripas", "Por Quilmes Oeste", geoLocation, paymentMethods)
    private val restaurant1: Restaurant = Restaurant(1, "Guerrin", "The best pizza of Bs. As.", "Corrientes 4321", geoLocation, paymentMethods)
    private val restaurant2: Restaurant = Restaurant(2, "Los Maizales", "inserte descripcion", "Calle Falsa 1234", geoLocation, paymentMethods)
    private val restaurant3: Restaurant = Restaurant(3, "Bar 1840", "inserte descripcion", "Corrientes 4320", geoLocation, paymentMethods)

    private val menu0 = Menu(0,"SodaMenu","with authentic sodas since 90's", mutableListOf(),restaurant0)
    private val menu1 = Menu(1,"ItalianFood","With Pepperoni", mutableListOf(),restaurant1)
    private val menu2 = Menu(2,"VegieMenu","No vives de ensalada", mutableListOf(),restaurant2)
    private val menu3 = Menu(3,"Kaloric","Porn food", mutableListOf(),restaurant3)

    private val product0 = Product(0, "IceCream", "Made with milk from happy cows", 20.0, Category.POSTRE)
    private val product1 = Product(1, "Pizza", "Really italian pepperoni pizza", 40.0, Category.PLATOPRINCIPAL)
    private val product2 = Product(2, "Soda", "Bzzz bzzz bzzz", 40.0, Category.PLATOPRINCIPAL)
    private val product3 = Product(3, "HotDog", "German sausage", 40.0, Category.PLATOPRINCIPAL)

    private val mapRestaurants: MutableMap<Int, Searchable> = mutableMapOf()
    private val mapMenus: MutableMap<Int, Searchable> = mutableMapOf()
    private val mapProducts: MutableMap<Int, Searchable> = mutableMapOf()

    private val restaurants : MutableCollection<Searchable> = mutableListOf(restaurant0, restaurant1, restaurant2, restaurant3)
    private val menus : MutableCollection<Searchable> = mutableListOf(menu0, menu1, menu2, menu3)
    private val products : MutableCollection<Searchable> = mutableListOf(product0, product1, product2, product3)

    private fun addElements(map : MutableMap<Int, Searchable>, list : MutableCollection<Searchable>) {
        list.forEach { element -> map.put(element.code, element) }
    }

    private fun addRestaurants() {
        val listRestaurant: MutableCollection<Searchable> = restaurants
        addElements(mapRestaurants, listRestaurant)
    }

    private fun addMenus() {
        val listMenus: MutableCollection<Searchable> = menus
        addElements(mapMenus, listMenus)
    }

    private fun addProducts() {
        val listProducts: MutableCollection<Searchable> = products
        addElements(mapProducts, listProducts)
    }

    //SEARCHING BY ID
    @Test
    fun test00_theSearchByIdGiveTheCorrectRestaurant() {
        val byId0 = CriteriaById(0)
        val byId1 = CriteriaById(1)
        val byId2 = CriteriaById(2)
        val byId3 = CriteriaById(3)

        var listResult : MutableCollection<Searchable?>
        addRestaurants()
        listResult = searcher.searchBy(byId0, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant0), listResult)
        listResult = searcher.searchBy(byId1, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant1), listResult)
        listResult = searcher.searchBy(byId2, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant2), listResult)
        listResult = searcher.searchBy(byId3, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant3), listResult)
    }

    @Test
    fun test01_theSearchByIdDontGiveTheCorrectRestaurantBecauseTheMapIsEmpty() {
        val fakeId = CriteriaById(666)
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant?>(), listResult)
    }

    @Test
    fun test02_theSearchByIdDontGiveTheCorrectRestaurantBecauseTheCodeDontExist() {
        val fakeId = CriteriaById(666)
        addRestaurants()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test03_theSearchByIdGiveTheCorrectMenu() {
        val byId0 = CriteriaById(0)
        val byId1 = CriteriaById(1)
        val byId2 = CriteriaById(2)
        val byId3 = CriteriaById(3)
        var listResult : MutableCollection<Searchable?>
        addMenus()
        listResult = searcher.searchBy(byId0, mapMenus)
        Assert.assertEquals(mutableListOf(menu0), listResult)
        listResult = searcher.searchBy(byId1, mapMenus)
        Assert.assertEquals(mutableListOf(menu1), listResult)
        listResult = searcher.searchBy(byId2, mapMenus)
        Assert.assertEquals(mutableListOf(menu2), listResult)
        listResult = searcher.searchBy(byId3, mapMenus)
        Assert.assertEquals(mutableListOf(menu3), listResult)
    }

    @Test
    fun test04_theSearchByIdDontGiveTheCorrectMenuBecauseTheMapIsEmpty() {
        val fakeId = CriteriaById(666)
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapMenus)
        Assert.assertEquals(mutableListOf<Restaurant?>(), listResult)
    }

    @Test
    fun test05_theSearchByIdDontGiveTheCorrectMenuBecauseTheCodeDontExist() {
        val fakeId = CriteriaById(666)
        addProducts()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapMenus)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test06_theSearchByIdGiveTheCorrectProduct() {
        val byId0 = CriteriaById(0)
        val byId1 = CriteriaById(1)
        val byId2 = CriteriaById(2)
        val byId3 = CriteriaById(3)
        var listResult : MutableCollection<Searchable?>
        addProducts()
        listResult = searcher.searchBy(byId0, mapProducts)
        Assert.assertEquals(mutableListOf(product0), listResult)
        listResult = searcher.searchBy(byId1, mapProducts)
        Assert.assertEquals(mutableListOf(product1), listResult)
        listResult = searcher.searchBy(byId2, mapProducts)
        Assert.assertEquals(mutableListOf(product2), listResult)
        listResult = searcher.searchBy(byId3, mapProducts)
        Assert.assertEquals(mutableListOf(product3), listResult)
    }

    @Test
    fun test07_theSearchByIdDontGiveTheCorrectProductBecauseTheMapIsEmpty() {
        val fakeId = CriteriaById(666)
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test08_theSearchByIdDontGiveTheCorrectProductBecauseTheCodeDontExist() {
        val fakeId = CriteriaById(666)
        addProducts()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    //SEARCHING BY STRING
    @Test
    fun test09_theSearchByStringGiveTheCorrectRestaurantWhenSearchByName(){
        val byString0 = CriteriaByString("El Tano")
        val byString1 = CriteriaByString("Guerrin")
        val byString2 = CriteriaByString("Los Maizales")
        val byString3 = CriteriaByString("Bar 1840")
        var listResult : MutableCollection<Searchable?>
        addRestaurants()
        listResult = searcher.searchBy(byString0, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant0), listResult)
        listResult = searcher.searchBy(byString1, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant1), listResult)
        listResult = searcher.searchBy(byString2, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant2), listResult)
        listResult = searcher.searchBy(byString3, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant3), listResult)
    }

    @Test
    fun test10_theSearchByStringDontGiveTheCorrectRestaurantBecauseTheNameDontExist() {
        val fakeId = CriteriaByString("Ozzymandias")
        addRestaurants()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test11_theSearchByStringDontGiveTheCorrectRestaurantBecauseTheMapIsEmpty() {
        val fakeId = CriteriaByString("Ozzymandias")
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test12_theSearchByStringGiveTheCorrectMenuWhenSearchByName(){
        val byString4 = CriteriaByString("SodaMenu")
        val byString5 = CriteriaByString("ItalianFood")
        val byString6 = CriteriaByString("VegieMenu")
        val byString7 = CriteriaByString("Kaloric")
        var listResult : MutableCollection<Searchable?>
        addMenus()
        listResult = searcher.searchBy(byString4, mapMenus)
        Assert.assertEquals(mutableListOf(menu0), listResult)
        listResult = searcher.searchBy(byString5, mapMenus)
        Assert.assertEquals(mutableListOf(menu1), listResult)
        listResult = searcher.searchBy(byString6, mapMenus)
        Assert.assertEquals(mutableListOf(menu2), listResult)
        listResult = searcher.searchBy(byString7, mapMenus)
        Assert.assertEquals(mutableListOf(menu3), listResult)
    }

    @Test
    fun test13_theSearchByStringDontGiveTheCorrectMenuBecauseTheNameDontExist() {
        val fakeId = CriteriaByString("Ozzymandias")
        addMenus()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapMenus)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test14_theSearchByStringDontGiveTheCorrectMenuBecauseTheMapIsEmpty() {
        val fakeId = CriteriaByString("Ozzymandias")
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test15_theSearchByStringGiveTheCorrectProductWhenSearchByName(){
        val byString8 = CriteriaByString("IceCream")
        val byString9 = CriteriaByString("Pizza")
        val byString10 = CriteriaByString("Soda")
        val byString11 = CriteriaByString("HotDog")
        var listResult : MutableCollection<Searchable?>
        addProducts()
        listResult = searcher.searchBy(byString8, mapProducts)
        Assert.assertEquals(mutableListOf(product0), listResult)
        listResult = searcher.searchBy(byString9, mapProducts)
        Assert.assertEquals(mutableListOf(product1), listResult)
        listResult = searcher.searchBy(byString10, mapProducts)
        Assert.assertEquals(mutableListOf(product2), listResult)
        listResult = searcher.searchBy(byString11, mapProducts)
        Assert.assertEquals(mutableListOf(product3), listResult)
    }

    @Test
    fun test16_theSearchByStringDontGiveTheCorrectProductBecauseTheNameDontExist() {
        val fakeId = CriteriaByString("Ozzymandias")
        addProducts()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test17_theSearchByStringDontGiveTheCorrectProductBecauseTheMapIsEmpty() {
        val fakeId = CriteriaByString("Ozzymandias")
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

   @Test
    fun test18_theSearchByIdAndStringGiveTheCorrectRestaurantWhenSearchByName(){
        val byIdAndString0 = CriteriaByIdAndString("El Tano")
        val byIdAndString1 = CriteriaByIdAndString("Guerrin")
        val byIdAndString2 = CriteriaByIdAndString("Los Maizales")
        val byIdAndString3 = CriteriaByIdAndString("1840")
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(byIdAndString0, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant0), listResult)

        listResult = searcher.searchBy(byIdAndString1, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant1), listResult)

        listResult = searcher.searchBy(byIdAndString2, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant2), listResult)

        listResult = searcher.searchBy(byIdAndString3, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant3), listResult)
    }

    @Test
    fun test19_theSearchByIdAndStringGiveTheCorrectRestaurantWhenSearchByCode(){
        val byIdAndString2 = CriteriaByIdAndString(2)
        val byIdAndString3 = CriteriaByIdAndString(3)
        var listResult : MutableCollection<Searchable?>
        addRestaurants()

        listResult = searcher.searchBy(byIdAndString2, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant2), listResult)
        listResult = searcher.searchBy(byIdAndString3, mapRestaurants)
        Assert.assertEquals(mutableListOf(restaurant3), listResult)
    }

    @Test
    fun test20_theSearchByIdAndStringGivePartiallyTheRestaurantsWhenSearchByCode(){
        val byIdAndString0 = CriteriaByIdAndString(0)
        val byIdAndString1 = CriteriaByIdAndString(1)
        addRestaurants()

        var listResult : MutableCollection<Searchable?> = searcher.searchBy(byIdAndString0, mapRestaurants)
        Assert.assertEquals(2, listResult.size)
        Assert.assertTrue(listResult.contains(restaurant0))
        Assert.assertTrue(listResult.contains(restaurant3))

        listResult = searcher.searchBy(byIdAndString1, mapRestaurants)
        Assert.assertEquals(2, listResult.size)
        Assert.assertTrue(listResult.contains(restaurant1))
        Assert.assertTrue(listResult.contains(restaurant3))
    }

    @Test
    fun test21_theSearchByIdAndStringDontGiveTheCorrectRestaurantBecauseTheNameDontExist() {
        val fakeId = CriteriaByIdAndString("Ozzymandias")
        addRestaurants()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test22_theSearchByIdAndStringDontGiveTheCorrectRestaurantBecauseTheCodeDontExist() {
        val fakeId = CriteriaByIdAndString(777)
        addRestaurants()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test23_theSearchByIdAndStringDontGiveTheCorrectRestaurantBecauseTheMapIsEmpty() {
        val fakeId = CriteriaByIdAndString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)

        val otheFakeId = CriteriaByIdAndString(100000)
        listResult = searcher.searchBy(otheFakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test24_theSearchByIdAndStringGiveTheCorrectMenuWhenSearchByName(){
        val byIdAndString0 = CriteriaByIdAndString("SodaMenu")
        val byIdAndString1 = CriteriaByIdAndString("ItalianFood")
        val byIdAndString2 = CriteriaByIdAndString("VegieMenu")
        val byIdAndString3 = CriteriaByIdAndString("Kaloric")
        addMenus()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(byIdAndString0, mapMenus)
        Assert.assertEquals(mutableListOf(menu0), listResult)
        listResult = searcher.searchBy(byIdAndString1, mapMenus)
        Assert.assertEquals(mutableListOf(menu1), listResult)
        listResult = searcher.searchBy(byIdAndString2, mapMenus)
        Assert.assertEquals(mutableListOf(menu2), listResult)
        listResult = searcher.searchBy(byIdAndString3, mapMenus)
        Assert.assertEquals(mutableListOf(menu3), listResult)
    }

    @Test
    fun test25_theSearchByIdAndStringGiveTheCorrectMenuWhenSearchByCode(){

        val byIdAndString0 = CriteriaByIdAndString(0)
        val byIdAndString1 = CriteriaByIdAndString(1)
        val byIdAndString2 = CriteriaByIdAndString(2)
        val byIdAndString3 = CriteriaByIdAndString(3)

        addMenus()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(byIdAndString0, mapMenus)
        Assert.assertEquals(mutableListOf(menu0), listResult)

        listResult = searcher.searchBy(byIdAndString1, mapMenus)
        Assert.assertEquals(mutableListOf(menu1), listResult)

        listResult = searcher.searchBy(byIdAndString2, mapMenus)
        Assert.assertEquals(mutableListOf(menu2), listResult)

        listResult = searcher.searchBy(byIdAndString3, mapMenus)
        Assert.assertEquals(mutableListOf(menu3), listResult)
    }

    @Test
    fun test26_theSearchByIdAndStringDontGiveTheCorrectMenuBecauseTheNameDontExist() {
        val fakeId = CriteriaByIdAndString("Ozzymandias")
        addRestaurants()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test27_theSearchByIdAndStringDontGiveTheCorrectMenuBecauseTheCodeDontExist() {
        val fakeId = CriteriaByIdAndString(777)
        addRestaurants()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test28_theSearchByIdAndStringDontGiveTheCorrectMenuBecauseTheMapIsEmpty() {
        val fakeId = CriteriaByIdAndString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)

        val otheFakeId = CriteriaByIdAndString(100000)
        listResult = searcher.searchBy(otheFakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test29_theSearchByIdAndStringGiveTheCorrectProductWhenSearchByName(){
        val byString8 = CriteriaByIdAndString("IceCream")
        val byString9 = CriteriaByIdAndString("Pizza")
        val byString10 = CriteriaByIdAndString("Soda")
        val byString11 = CriteriaByIdAndString("HotDog")
        var listResult : MutableCollection<Searchable?>
        addProducts()
        listResult = searcher.searchBy(byString8, mapProducts)
        Assert.assertEquals(mutableListOf(product0), listResult)
        listResult = searcher.searchBy(byString9, mapProducts)
        Assert.assertEquals(mutableListOf(product1), listResult)
        listResult = searcher.searchBy(byString10, mapProducts)
        Assert.assertEquals(mutableListOf(product2), listResult)
        listResult = searcher.searchBy(byString11, mapProducts)
        Assert.assertEquals(mutableListOf(product3), listResult)
    }

    @Test
    fun test30_theSearchByIdAndStringDontGiveTheCorrectProductBecauseTheNameDontExist() {
        val fakeId = CriteriaByIdAndString("Ozzymandias")
        addProducts()
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test31_theSearchByIdAndStringDontGiveTheCorrectProductBecauseTheMapIsEmpty() {
        val fakeId = CriteriaByIdAndString("Ozzymandias")
        val listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }
}
