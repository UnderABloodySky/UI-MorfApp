package discount

abstract class Discount (protected val name : String, protected val value : Double ){

    /*
    *   Falta excepcion que value > 0
    *   Name esta de mas.
    * */

    abstract fun processDiscount(price: Double): Double;

    //Podriamos hacer un templated y darle cuerpo a este mensaje
    // processDiscount(price: Double) : Double = price - this.discount()

    //abstract fun discount(price: Double) : Double
    //Nos ahorramos de restar dos veces.

}