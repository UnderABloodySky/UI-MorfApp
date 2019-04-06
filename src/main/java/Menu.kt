class Menu {

    var code        = Int;
    var name        = String;
    var description = String;
    var price       = Double;
    var products    = ArrayList<Product>();
    var cost        = Double.Companion;
    var discount    = Discount();
    var enabled     = Boolean;


    fun costAutocalculation(): Unit {
        var totalPrice = totalPrice();
        cost = totalPrice - (totalPrice * discount.value / 100);

    }

    fun totalPrice(): Double {
        var total = 0.0;
        products.forEach { total = total + it.price };
        return total;
    }
}