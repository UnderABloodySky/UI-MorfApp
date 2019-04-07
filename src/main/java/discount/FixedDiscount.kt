package discount

class FixedDiscount(): Discount(){

    override fun processDiscount(price: Double): Double {
        return price - this.value as Double;
    }
}