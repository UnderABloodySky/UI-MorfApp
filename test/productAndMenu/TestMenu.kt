package productAndMenu

import applicationModel.MorfApp
import geoclase.Geo
import org.junit.Test
import org.junit.Assert
import paymentMethod.Cash
import paymentMethod.PaymentMethod
import restaurant.Restaurant

class TestMenu {

    private var applicationModel : MorfApp = MorfApp
    private var cash : PaymentMethod = Cash()
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.BEBIDA)
    private var hotDog = Product(2, "HotDog", "Original Deustch Sausage", 120.0, Category.PLATOPRINCIPAL)
    private var geoLocation = Geo(2.0,2.0)
    private var restaurant : Restaurant = applicationModel.createRestaurant("Otro nombre", "Otra descripcion", "asd", geoLocation, mutableListOf(cash))


    @Test
    fun addANewProductToAnEmptyListAndCheckIfSizeIsAsEspected() {
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf<Product>(), restaurant)
        Assert.assertEquals(menu.productsOfMenu.size, 0)
        menu.addProductToMenu(this.soda)
        Assert.assertEquals(menu.productsOfMenu.size, 1)
    }

    @Test
    fun removeAProductOfAMenuThatHasOnlyOneAndCheckIfSizeIsAsEspected() {
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf<Product>(), restaurant)
        menu.addProductToMenu(this.soda)
        Assert.assertEquals(menu.productsOfMenu.size, 1)
        menu.removeProductFromMenu(this.soda.code)
        Assert.assertEquals(menu.productsOfMenu.size, 0)
    }

    @Test
    fun CheckThatIfIHaveTwoOfTheSameProductsAndIRemoveOneOfThemTheSizeIsZero() {
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf(), restaurant)
        menu.addProductToMenu(this.soda)
        menu.addProductToMenu(this.soda)
        menu.removeProductFromMenu(this.soda.code)
        Assert.assertEquals(0, menu.productsOfMenu.size)
    }

    @Test
    fun AddTwoProductsWithDifferentPricesToAMenuAndCheckIfTheTotalValueOfMenuIsAsEspected() {
        var menu = Menu(1, "HotSoda", "Hot soda dog", mutableListOf<Product>(), restaurant)
        menu.addProductToMenu(this.soda)
        menu.addProductToMenu(this.hotDog)
        Assert.assertEquals(menu.totalPrice(), 200.0, 0.0)
    }

    @Test
    fun checkThatIfThereIsNoDiscountAppliedToTheMenuItStillReturnsTheTotalValueWhenAskedForCostAutocalculation() {
        var menu = Menu(1, "HotSoda", "Hot soda dog", mutableListOf<Product>(), restaurant)
        menu.productsOfMenu.add(this.soda)
        Assert.assertEquals(menu.costAutocalculation(), 80.0, 0.0)
    }
}