package productAndMenu

import org.junit.Test
import junit.framework.TestCase.assertEquals

class TestMenu {
    @Test fun addANewProductToAnEmptyListAndCheckIfSizeIsAsEspected() {
        var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK);
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", MutableCollection(), null, true);
        AssertEquals(menu.size(), 0);
        menu.addProductToMenu(soda);
        AssertEquals(menu.size(), 1);
    }
    @Test fun removeAProductOfAMenuThatHasOnlyOneAndCheckIfSizeIsAsEspected() {
        var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK);
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", MutableCollection(), null, true);
        menu.addProductToMenu(soda);
        AssertEquals(menu.size(), 1);
        menu.removeProductFromMenu(soda);
        AssertEquals(menu.size(), 0);
    }
    @Test fun CheckThatIfIHaveTwoOfTheSameProductsAndIRemoveOneOfThemTheSizeIsOne() {
        var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK);
        var menu = Menu(1, "SodaMenu", "with authentic sodas since 90's", MutableCollection(), null, true);
        menu.addProductToMenu(soda);
        menu.addProductToMenu(soda);
        menu.removeProductFromMenu(soda);
        AssertEquals(menu.size(), 1);
    }
    @Test fun AddTwoProductsWithDifferentPricesToAMenuAndCheckIfTheTotalValueOfMenuIsAsEspected() {
        var soda   = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK);
        var hotDog = Product(2, "HotDog", "Original Deustch Sausage", 120.0, Category.MAINDISH);
        var menu   = Menu(1, "SodaMenu", "with authentic sodas since 90's", MutableCollection(), null, true);
        menu.addProductToMenu(soda);
        menu.addProductToMenu(hotDog);
        AssertEquals(menu.totalPrice(), 200.0)
    }
    //Faltan Tests de descuentos.
}