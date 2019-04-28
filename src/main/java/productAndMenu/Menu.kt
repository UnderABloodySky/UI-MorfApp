package productAndMenu

import discount.Discount
import discount.NoDiscount
import restaurant.Restaurant

class Menu(val code: Int,
           var name: String,
           var description: String,
           var products: MutableCollection<Product>,
           var restaurant : Restaurant,
           var discount: Discount = NoDiscount(),
           var enabled: Boolean = true) {


    fun addProductToMenu(product: Product): Unit { this.products.add(product); }

    fun costAutocalculation(): Double { return this.discount.processDiscount(totalPrice()); }

    fun enabled():Boolean { return this.enabled; }

    fun removeProductFromMenu(product: Product): Unit { this.products.remove(product); }

    fun totalPrice(): Double  = this.products.sumByDouble { it.price };

    fun containProductWith(code: Int?):Boolean{

       return  products.any{product -> product.code==code  }

    }
}
