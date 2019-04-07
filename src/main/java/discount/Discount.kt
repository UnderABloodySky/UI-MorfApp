package discount

abstract class Discount {

    var name     = String;
    var value    = Double;

    abstract fun processDiscount(price: Double): Double;

}