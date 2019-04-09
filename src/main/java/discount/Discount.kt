package discount

abstract class Discount (protected val name : String, protected val value : Double ){

    /*
    *   Falta excepcion que value > 0
    *   Name esta de mas.
    * */

    abstract fun processDiscount(price: Double): Double;

}