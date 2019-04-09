package productAndMenu

import discount.Discount

class Menu(val code: Int,
           var name: String,
           var description: String,
           var products: MutableCollection<Product>,
           var discount: Discount?,
           var enabled: Boolean) {

    fun addProductToMenu(product: Product): Unit { this.products.add(product); }

    fun costAutocalculation(): Double {
        if (this.discount == null){
            return this.totalPrice();
        }else{
            return discount?.processDiscount(totalPrice()) as Double;
        }
    }

    fun enabled():Boolean { return enabled }

    fun removeProductFromMenu(product: Product): Unit { this.products.remove(product);}

    fun totalPrice(): Double  = products.sumByDouble { it.price };
}
