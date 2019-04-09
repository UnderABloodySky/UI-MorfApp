package productAndMenu

import org.junit.Test
import org.junit.Assert

class TestMenu {

    var soda = Product(1, "Soda", "with authentic bubbles", 80.0, Category.DRINK);
    var hotDog = Product(2, "HotDog", "Original Deustch Sausage", 120.0, Category.MAINDISH);

    @Test fun addANewProductToAnEmptyListAndCheckIfSizeIsAsEspected() {
        var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);
        Assert.assertEquals(menu.products.size, 0);
        menu.addProductToMenu(this.soda);
        Assert.assertEquals(menu.products.size, 1);
    }
    @Test fun removeAProductOfAMenuThatHasOnlyOneAndCheckIfSizeIsAsEspected() {
        var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);
        menu.addProductToMenu(this.soda);
        Assert.assertEquals(menu.products.size, 1);
        menu.removeProductFromMenu(this.soda);
        Assert.assertEquals(menu.products.size, 0);
    }
    @Test fun CheckThatIfIHaveTwoOfTheSameProductsAndIRemoveOneOfThemTheSizeIsOne() {
        var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);
        menu.addProductToMenu(this.soda);
        menu.addProductToMenu(this.soda);
        menu.removeProductFromMenu(this.soda);
        Assert.assertEquals(menu.products.size, 1);
    }
    @Test fun AddTwoProductsWithDifferentPricesToAMenuAndCheckIfTheTotalValueOfMenuIsAsEspected() {
        var menu = Menu(1,"HotSoda","Hot soda dog", mutableListOf<Product>(),null,true);
        menu.addProductToMenu(this.soda);
        menu.addProductToMenu(this.hotDog);
        Assert.assertEquals(menu.totalPrice(), 200.0, 0.0);
    }
    @Test fun checkThatIfThereIsNoDiscountAppliedToTheMenuItStillReturnsTheTotalValueWhenAskedForCostAutocalculation() {
        var menu = Menu(1,"HotSoda","Hot soda dog", mutableListOf<Product>(),null,true);
        menu.products.add(this.soda);
        Assert.assertEquals(menu.costAutocalculation(), 80.0, 0.0);
    }

}