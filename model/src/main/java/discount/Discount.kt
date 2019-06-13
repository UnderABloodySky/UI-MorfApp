package discount

abstract class Discount (val name : String, val description : String, var value : Double ){
    fun processDiscount(price: Double): Double {
        var total = price - discount(price)
        if (total < 0)
            total = 0.0
        return total
    }

    abstract fun discount(price: Double) : Double
}