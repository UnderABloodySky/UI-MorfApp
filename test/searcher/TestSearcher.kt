package searcher

import applicationModel.MorfApp
import geoclaseui.Geo
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

    private var searcher: Searcher = Searcher()

    private var applicationModel: MorfApp = MorfApp
    private var geoLocation: Geo = Geo(2.0, 1.0)

    private var cash : PaymentMethod  = Cash()
    private var debit : PaymentMethod = Debit()
    private var paymentMethods : MutableCollection<PaymentMethod> = mutableListOf(cash, debit)
    private var restaurant0: Restaurant = Restaurant(0, "El Tano", "LLenadero magico de tripas", "Por Quilmes Oeste", geoLocation, paymentMethods)
    private var restaurant1: Restaurant = Restaurant(1, "Guerrin", "The best pizza of Bs. As.", "Corrientes 4321", geoLocation, paymentMethods)
    private var restaurant2: Restaurant = Restaurant(2, "Los Maizales", "inserte descripcion", "Calle Falsa 1234", geoLocation, paymentMethods)
    private var restaurant3: Restaurant = Restaurant(3, "Bar 1840", "inserte descripcion", "Corrientes 4320", geoLocation, paymentMethods)

    private var menu0 = Menu(0,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),restaurant0)
    private var menu1 = Menu(1,"ItalianFood","With Pepperoni", mutableListOf<Product>(),restaurant1)
    private var menu2 = Menu(2,"VegieMenu","No vives de ensalada", mutableListOf<Product>(),restaurant2)
    private var menu3 = Menu(3,"Kaloric","Porn food", mutableListOf<Product>(),restaurant3)

    private var product0 = Product(0, "IceCream", "Made with milk from happy cows", 20.0, Category.DISSERT)
    private var product1 = Product(1, "Pizza", "Really italian pepperoni pizza", 40.0, Category.MAINDISH)
    private var product2 = Product(2, "Soda", "Bzzz bzzz bzzz", 40.0, Category.MAINDISH)
    private var product3 = Product(3, "HotDog", "German sausage", 40.0, Category.MAINDISH)

    private var mapRestaurants: MutableMap<Int, Searchable> = mutableMapOf()
    private var mapMenus: MutableMap<Int, Searchable> = mutableMapOf()
    private var mapProducts: MutableMap<Int, Searchable> = mutableMapOf()

    private var restaurants : MutableCollection<Searchable> = mutableListOf(restaurant0, restaurant1, restaurant2, restaurant3)
    private var menus : MutableCollection<Searchable> = mutableListOf(menu0, menu1, menu2, menu3)
    private var products : MutableCollection<Searchable> = mutableListOf(product0, product1, product2, product3)

    private fun addElements(map : MutableMap<Int, Searchable>, list : MutableCollection<Searchable>) : Unit {
        list.forEach { element -> map.put(element.code, element) }
    }

    private fun addRestaurants(): Unit {
        var listRestaurant: MutableCollection<Searchable> = restaurants
        addElements(mapRestaurants, listRestaurant)
    }

    private fun addMenus(): Unit {
        var listMenus: MutableCollection<Searchable> = menus
        addElements(mapMenus, listMenus)
    }

    private fun addProducts(): Unit {
        var listProducts: MutableCollection<Searchable> = products
        addElements(mapProducts, listProducts)
    }

    //SEARCHING BY ID
    @Test
    fun test00_theSearchByIdGiveTheCorrectRestaurant() {
        var byId0 : CriteriaById = CriteriaById(0)
        var byId1 : CriteriaById = CriteriaById(1)
        var byId2 : CriteriaById = CriteriaById(2)
        var byId3 : CriteriaById = CriteriaById(3)

        var listResult : MutableCollection<Searchable?> = mutableListOf<Searchable?>()
        addRestaurants()
        listResult = searcher.searchBy(byId0, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant0), listResult)
        listResult = searcher.searchBy(byId1, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant1), listResult)
        listResult = searcher.searchBy(byId2, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant2), listResult)
        listResult = searcher.searchBy(byId3, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant3), listResult)
    }

    @Test
    fun test01_theSearchByIdDontGiveTheCorrectRestaurantBecauseTheMapIsEmpty() {
        var fakeId : CriteriaById = CriteriaById(666)
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant?>(null), listResult)
    }

    @Test
    fun test02_theSearchByIdDontGiveTheCorrectRestaurantBecauseTheCodeDontExist() {
        var fakeId : CriteriaById = CriteriaById(666)
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    @Test
    fun test03_theSearchByIdGiveTheCorrectMenu() {
        var byId0 : CriteriaById = CriteriaById(0)
        var byId1 : CriteriaById = CriteriaById(1)
        var byId2 : CriteriaById = CriteriaById(2)
        var byId3 : CriteriaById = CriteriaById(3)
        var listResult : MutableCollection<Searchable?> = mutableListOf()
        addMenus()
        listResult = searcher.searchBy(byId0, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu0), listResult)
        listResult = searcher.searchBy(byId1, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu1), listResult)
        listResult = searcher.searchBy(byId2, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu2), listResult)
        listResult = searcher.searchBy(byId3, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu3), listResult)
    }

    @Test
    fun test04_theSearchByIdDontGiveTheCorrectMenuBecauseTheMapIsEmpty() {
        var fakeId : CriteriaById = CriteriaById(666)
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapMenus)
        Assert.assertEquals(mutableListOf<Restaurant?>(null), listResult)
    }

    @Test
    fun test05_theSearchByIdDontGiveTheCorrectMenuBecauseTheCodeDontExist() {
        var fakeId : CriteriaById = CriteriaById(666)
        addProducts()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapMenus)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    @Test
    fun test06_theSearchByIdGiveTheCorrectProduct() {
        var byId0 : CriteriaById = CriteriaById(0)
        var byId1 : CriteriaById = CriteriaById(1)
        var byId2 : CriteriaById = CriteriaById(2)
        var byId3 : CriteriaById = CriteriaById(3)
        var listResult : MutableCollection<Searchable?> = mutableListOf()
        addProducts()
        listResult = searcher.searchBy(byId0, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product0), listResult)
        listResult = searcher.searchBy(byId1, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product1), listResult)
        listResult = searcher.searchBy(byId2, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product2), listResult)
        listResult = searcher.searchBy(byId3, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product3), listResult)
    }

    @Test
    fun test07_theSearchByIdDontGiveTheCorrectProductBecauseTheMapIsEmpty() {
        var fakeId : CriteriaById = CriteriaById(666)
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    @Test
    fun test08_theSearchByIdDontGiveTheCorrectProductBecauseTheCodeDontExist() {
        var fakeId : CriteriaById = CriteriaById(666)
        addProducts()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    //SEARCHING BY STRING
    @Test
    fun test09_theSearchByStringGiveTheCorrectRestaurantWhenSearchByName(){
        var byString0: CriteriaByString = CriteriaByString("El Tano")
        var byString1: CriteriaByString = CriteriaByString("Guerrin")
        var byString2: CriteriaByString = CriteriaByString("Los Maizales")
        var byString3: CriteriaByString = CriteriaByString("Bar 1840")
        var listResult : MutableCollection<Searchable?> = mutableListOf<Searchable?>()
        addRestaurants()
        listResult = searcher.searchBy(byString0, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant0), listResult)
        listResult = searcher.searchBy(byString1, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant1), listResult)
        listResult = searcher.searchBy(byString2, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant2), listResult)
        listResult = searcher.searchBy(byString3, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant3), listResult)
    }

    @Test
    fun test10_theSearchByStringDontGiveTheCorrectRestaurantBecauseTheNameDontExist() {
        var fakeId : CriteriaByString = CriteriaByString("Ozzymandias")
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test11_theSearchByStringDontGiveTheCorrectRestaurantBecauseTheMapIsEmpty() {
        var fakeId : CriteriaByString = CriteriaByString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test12_theSearchByStringGiveTheCorrectMenuWhenSearchByName(){
        var byString4: CriteriaByString = CriteriaByString("SodaMenu")
        var byString5: CriteriaByString = CriteriaByString("ItalianFood")
        var byString6: CriteriaByString = CriteriaByString("VegieMenu")
        var byString7: CriteriaByString = CriteriaByString("Kaloric")
        var listResult : MutableCollection<Searchable?> = mutableListOf<Searchable?>()
        addMenus()
        listResult = searcher.searchBy(byString4, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu0), listResult)
        listResult = searcher.searchBy(byString5, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu1), listResult)
        listResult = searcher.searchBy(byString6, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu2), listResult)
        listResult = searcher.searchBy(byString7, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu3), listResult)
    }

    @Test
    fun test13_theSearchByStringDontGiveTheCorrectMenuBecauseTheNameDontExist() {
        var fakeId : CriteriaByString = CriteriaByString("Ozzymandias")
        addMenus()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapMenus)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test14_theSearchByStringDontGiveTheCorrectMenuBecauseTheMapIsEmpty() {
        var fakeId : CriteriaByString = CriteriaByString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test15_theSearchByStringGiveTheCorrectProductWhenSearchByName(){
        var byString8: CriteriaByString = CriteriaByString("IceCream")
        var byString9: CriteriaByString = CriteriaByString("Pizza")
        var byString10: CriteriaByString = CriteriaByString("Soda")
        var byString11: CriteriaByString = CriteriaByString("HotDog")
        var listResult : MutableCollection<Searchable?> = mutableListOf<Searchable?>()
        addProducts()
        listResult = searcher.searchBy(byString8, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product0), listResult)
        listResult = searcher.searchBy(byString9, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product1), listResult)
        listResult = searcher.searchBy(byString10, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product2), listResult)
        listResult = searcher.searchBy(byString11, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product3), listResult)
    }

    @Test
    fun test16_theSearchByStringDontGiveTheCorrectProductBecauseTheNameDontExist() {
        var fakeId : CriteriaByString = CriteriaByString("Ozzymandias")
        addProducts()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test17_theSearchByStringDontGiveTheCorrectProductBecauseTheMapIsEmpty() {
        var fakeId : CriteriaByString = CriteriaByString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

   @Test
    fun test18_theSearchByIdAndStringGiveTheCorrectRestaurantWhenSearchByName(){
        var byIdAndString0 : CriteriaByIdAndString = CriteriaByIdAndString("El Tano")
        var byIdAndString1 : CriteriaByIdAndString = CriteriaByIdAndString("Guerrin")
        var byIdAndString2 : CriteriaByIdAndString = CriteriaByIdAndString("Los Maizales")
        var byIdAndString3 : CriteriaByIdAndString = CriteriaByIdAndString("1840")
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(byIdAndString0, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant0), listResult)

        listResult = searcher.searchBy(byIdAndString1, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant1), listResult)

        listResult = searcher.searchBy(byIdAndString2, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant2), listResult)

        listResult = searcher.searchBy(byIdAndString3, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant3), listResult)
    }

    @Test
    fun test19_theSearchByIdAndStringGiveTheCorrectRestaurantWhenSearchByCode(){
        var byIdAndString0 : CriteriaByIdAndString = CriteriaByIdAndString(0)
        var byIdAndString1 : CriteriaByIdAndString = CriteriaByIdAndString(1)
        var byIdAndString2 : CriteriaByIdAndString = CriteriaByIdAndString(2)
        var byIdAndString3 : CriteriaByIdAndString = CriteriaByIdAndString(3)
        var listResult : MutableCollection<Searchable?>
        addRestaurants()

        listResult = searcher.searchBy(byIdAndString2, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant2), listResult)
        listResult = searcher.searchBy(byIdAndString3, mapRestaurants)
        Assert.assertEquals(mutableListOf<Restaurant>(restaurant3), listResult)
    }

    @Test
    fun test20_theSearchByIdAndStringGivePartiallyTheRestaurantsWhenSearchByCode(){
        var byIdAndString0 : CriteriaByIdAndString = CriteriaByIdAndString(0)
        var byIdAndString1 : CriteriaByIdAndString = CriteriaByIdAndString(1)
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
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString("Ozzymandias")
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test22_theSearchByIdAndStringDontGiveTheCorrectRestaurantBecauseTheCodeDontExist() {
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString(777)
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    @Test
    fun test23_theSearchByIdAndStringDontGiveTheCorrectRestaurantBecauseTheMapIsEmpty() {
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)

        var otheFakeId : CriteriaByIdAndString = CriteriaByIdAndString(100000)
        listResult = searcher.searchBy(otheFakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    @Test
    fun test24_theSearchByIdAndStringGiveTheCorrectMenuWhenSearchByName(){
        var byIdAndString0: CriteriaByIdAndString = CriteriaByIdAndString("SodaMenu")
        var byIdAndString1: CriteriaByIdAndString = CriteriaByIdAndString("ItalianFood")
        var byIdAndString2: CriteriaByIdAndString = CriteriaByIdAndString("VegieMenu")
        var byIdAndString3: CriteriaByIdAndString = CriteriaByIdAndString("Kaloric")
        addMenus()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(byIdAndString0, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu0), listResult)
        listResult = searcher.searchBy(byIdAndString1, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu1), listResult)
        listResult = searcher.searchBy(byIdAndString2, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu2), listResult)
        listResult = searcher.searchBy(byIdAndString3, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu3), listResult)
    }

    @Test
    fun test25_theSearchByIdAndStringGiveTheCorrectMenuWhenSearchByCode(){

        var byIdAndString0: CriteriaByIdAndString = CriteriaByIdAndString(0)
        var byIdAndString1: CriteriaByIdAndString = CriteriaByIdAndString(1)
        var byIdAndString2: CriteriaByIdAndString = CriteriaByIdAndString(2)
        var byIdAndString3: CriteriaByIdAndString = CriteriaByIdAndString(3)

        addMenus()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(byIdAndString0, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu0), listResult)

        listResult = searcher.searchBy(byIdAndString1, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu1), listResult)

        listResult = searcher.searchBy(byIdAndString2, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu2), listResult)

        listResult = searcher.searchBy(byIdAndString3, mapMenus)
        Assert.assertEquals(mutableListOf<Menu>(menu3), listResult)
    }

    @Test
    fun test26_theSearchByIdAndStringDontGiveTheCorrectMenuBecauseTheNameDontExist() {
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString("Ozzymandias")
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test27_theSearchByIdAndStringDontGiveTheCorrectMenuBecauseTheCodeDontExist() {
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString(777)
        addRestaurants()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    @Test
    fun test28_theSearchByIdAndStringDontGiveTheCorrectMenuBecauseTheMapIsEmpty() {
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)

        var otheFakeId : CriteriaByIdAndString = CriteriaByIdAndString(100000)
        listResult = searcher.searchBy(otheFakeId, mapRestaurants)
        Assert.assertEquals(mutableListOf<Product?>(null), listResult)
    }

    @Test
    fun test29_theSearchByIdAndStringGiveTheCorrectProductWhenSearchByName(){
        var byString8: CriteriaByIdAndString = CriteriaByIdAndString("IceCream")
        var byString9: CriteriaByIdAndString = CriteriaByIdAndString("Pizza")
        var byString10: CriteriaByIdAndString = CriteriaByIdAndString("Soda")
        var byString11: CriteriaByIdAndString = CriteriaByIdAndString("HotDog")
        var listResult : MutableCollection<Searchable?> = mutableListOf<Searchable?>()
        addProducts()
        listResult = searcher.searchBy(byString8, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product0), listResult)
        listResult = searcher.searchBy(byString9, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product1), listResult)
        listResult = searcher.searchBy(byString10, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product2), listResult)
        listResult = searcher.searchBy(byString11, mapProducts)
        Assert.assertEquals(mutableListOf<Product>(product3), listResult)
    }

    @Test
    fun test30_theSearchByIdAndStringDontGiveTheCorrectProductBecauseTheNameDontExist() {
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString("Ozzymandias")
        addProducts()
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }

    @Test
    fun test31_theSearchByIdAndStringDontGiveTheCorrectProductBecauseTheMapIsEmpty() {
        var fakeId : CriteriaByIdAndString = CriteriaByIdAndString("Ozzymandias")
        var listResult : MutableCollection<Searchable?> = searcher.searchBy(fakeId, mapProducts)
        Assert.assertEquals(mutableListOf<Product?>(), listResult)
    }
}
