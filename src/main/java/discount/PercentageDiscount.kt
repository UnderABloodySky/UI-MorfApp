package discount

class PercentageDiscount(name : String, value : Double): Discount(name, value) {
    override fun processDiscount(price: Double): Double {
        return price * this.value as Double / 100;
    }
}