package discount

abstract class Discount (val name : String, var value : Double ){

    fun processDiscount(price: Double): Double = price - discount(price)

    abstract fun discount(price: Double) : Double
}