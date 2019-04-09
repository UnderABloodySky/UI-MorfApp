package discount

abstract class Discount (protected val name : String, protected val value : Double ){

    /*
    *   Falta excepcion que value > 0
    *   Name esta de mas.
    * */

    //Templated
    fun processDiscount(price: Double): Double = price - discount(price);

    //Hook
    abstract fun discount(price: Double) : Double
    //Nos ahorramos de restar dos veces.

}