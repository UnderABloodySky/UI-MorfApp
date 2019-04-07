package discount

class PercentageDiscount(): Discount() {
    override fun processDiscount(price: Double): Double {
        return price * this.value as Double / 100;
    }
}