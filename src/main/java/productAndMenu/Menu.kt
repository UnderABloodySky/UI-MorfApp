package productAndMenu

import discount.Discount

class Menu {

    var code                    = Int;
    var name                    = String;
    var description             = String;
    var price                   = Double;
    var products                = mutableListOf<Product>();
    var cost                    = Double;
    var discount: Discount?     = null;
    var enabled                 = Boolean;


    fun costAutocalculation() = discount?.processDiscount(totalPrice());
    fun totalPrice(): Double  = products.sumByDouble { it.price as Double };
    fun addProductToMenu(product: Product): Unit {

        this.products.add(product);
    }
}
