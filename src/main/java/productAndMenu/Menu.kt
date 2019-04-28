package productAndMenu

import discount.Discount
import discount.NoDiscount
import restaurant.Restaurant

class Menu(val code: Int,
           var name: String,
           var description: String,
           var productsOfMenu: MutableList<Product>,
           var restaurant : Restaurant,
           var discount: Discount = NoDiscount(),
           var enabled: Boolean = true) {


    fun addProductToMenu(product: Product): Unit { this.productsOfMenu.add(product); }

    fun costAutocalculation(): Double { return this.discount.processDiscount(totalPrice()); }

    fun enabled():Boolean { return this.enabled; }

    fun removeProductFromMenu(product: Product): Unit { this.productsOfMenu.remove(product); }

    fun totalPrice(): Double  = this.productsOfMenu.sumByDouble { it.price };

    fun containProductWith(code: Int?):Boolean{

       return  productsOfMenu.any{product -> product.code==code  }

    }
    fun currenTotal():Double{
        var total=0.00
        productsOfMenu.forEach { product -> total = total + product.price }

        return total;
    }
}
