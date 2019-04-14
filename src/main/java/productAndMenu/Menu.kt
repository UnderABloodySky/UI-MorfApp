package productAndMenu

import discount.Discount
import discount.NoDiscount

class Menu(val code: Int,
           var name: String,
           var description: String,
           var products: MutableCollection<Product>,
           var discount: Discount,
           var enabled: Boolean) {


    fun addProductToMenu(product: Product): Unit { this.products.add(product); }

    fun costAutocalculation(): Double { return this.discount.processDiscount(totalPrice()); }

    fun enabled():Boolean { return this.enabled; }

    fun removeProductFromMenu(product: Product): Unit { this.products.remove(product); }

    fun totalPrice(): Double  = this.products.sumByDouble { it.price };
}
