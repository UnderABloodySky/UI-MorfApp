package productAndMenu

import discount.Discount
import discount.NoDiscount
import restaurant.Restaurant
import searcher.Searchable

class Menu(code: Int,
           name: String,
           description: String,
           var productsOfMenu: MutableList<Product>,
           var restaurant : Restaurant,
           var discount: Discount = NoDiscount(),
           var enabled: Boolean = true) : Searchable(code, name, description){


    fun addProductToMenu(product: Product): Unit { this.productsOfMenu.add(product); }

    fun costAutocalculation(): Double { return this.discount.processDiscount(totalPrice()); }

    fun enabled():Boolean { return this.enabled; }

    fun removeProductFromMenu(code: Int): Unit {
        var tempProductList = mutableListOf<Product>();
        this.productsOfMenu.forEach {
                    if (!(it.code == code)){
                        tempProductList.add(it);
                    }
        }
        this.productsOfMenu = tempProductList;
    }

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
