package discount

class NoDiscount(): Discount("NoDiscount", 0.0){

    override fun discount(price: Double): Double {

        return this.value;
    }
}