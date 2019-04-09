package discount

class PercentageDiscount(name : String, value : Double): Discount(name, value) {
    override fun processDiscount(price: Double): Double {
        //Verificar si value > 0 y < 100
        return price - price * this.value / 100;
    }
}