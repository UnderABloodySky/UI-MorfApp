package discount

abstract class Discount (protected val name : String, protected val value : Double ){

    fun processDiscount(price: Double): Double = price - discount(price);

    abstract fun discount(price: Double) : Double
}