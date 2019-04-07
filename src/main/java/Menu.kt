class Menu {

    var code        = Int;
    var name        = String;
    var description = String;
    var price       = Double;
    var products    = mutableListOf<Product>();
    var cost        = Double;
    var discount    = Discount();
    var enabled     = Boolean;


    fun costAutocalculation() {
        var tp: Double = totalPrice();
    }

    fun totalPrice(): Double = products.sumByDouble { it.price as Double };
    fun percentage(total: Double): Double = total.times(discount.value as Double).div(100);



    }
}