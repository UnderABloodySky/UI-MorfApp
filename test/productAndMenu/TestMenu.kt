package productAndMenu

import applicationModel.MorfApp
import geoclaseui.Geo
import org.junit.Test
import org.junit.Assert
import paymentMethod.Cash
import paymentMethod.PaymentMethod
import restaurant.Restaurant

class TestMenu {

    private var applicationModel : MorfApp = MorfApp;
    private var cash : PaymentMethod = Cash()
    private var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK);
    private var hotDog = Product(2, "HotDog", "Original Deustch Sausage", 120.0, Category.MAINDISH);
    private var geoLocation = Geo(2.0,2.0)
    private var restaurant : Restaurant = Restaurant(1, "El Tano", "inserte descripcion", "por quilmes oeste", geoLocation, mutableListOf(cash));

    @Test
    fun addANewProductToAnEmptyListAndCheckIfSizeIsAsEspected() {
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf<Product>(), restaurant);
        Assert.assertEquals(menu.products.size, 0);
        menu.addProductToMenu(this.soda);
        Assert.assertEquals(menu.products.size, 1);
    }

    @Test
    fun removeAProductOfAMenuThatHasOnlyOneAndCheckIfSizeIsAsEspected() {
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf<Product>(), restaurant);
        menu.addProductToMenu(this.soda);
        Assert.assertEquals(menu.products.size, 1);
        menu.removeProductFromMenu(this.soda);
        Assert.assertEquals(menu.products.size, 0);
    }

    @Test
    fun CheckThatIfIHaveTwoOfTheSameProductsAndIRemoveOneOfThemTheSizeIsOne() {
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", mutableListOf<Product>(), restaurant);
        menu.addProductToMenu(this.soda);
        menu.addProductToMenu(this.soda);
        menu.removeProductFromMenu(this.soda);
        Assert.assertEquals(menu.products.size, 1);
    }

    @Test
    fun AddTwoProductsWithDifferentPricesToAMenuAndCheckIfTheTotalValueOfMenuIsAsEspected() {
        var menu = Menu(1, "HotSoda", "Hot soda dog", mutableListOf<Product>(), restaurant);
        menu.addProductToMenu(this.soda);
        menu.addProductToMenu(this.hotDog);
        Assert.assertEquals(menu.totalPrice(), 200.0, 0.0);
    }

    @Test
    fun checkThatIfThereIsNoDiscountAppliedToTheMenuItStillReturnsTheTotalValueWhenAskedForCostAutocalculation() {
        var menu = Menu(1, "HotSoda", "Hot soda dog", mutableListOf<Product>(), restaurant);
        menu.products.add(this.soda);
        Assert.assertEquals(menu.costAutocalculation(), 80.0, 0.0);
    }
}