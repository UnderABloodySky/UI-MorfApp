package discount

class NoDiscount(name: String, value : Double ): Discount(name, value){

    override fun discount(price: Double): Double {

        return 0.0;
    }
}