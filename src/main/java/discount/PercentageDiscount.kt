package discount

class PercentageDiscount(name : String, value : Double): Discount(name, value) {
    override fun discount(price: Double): Double {
        //Verificar si value > 0 y < 100
        return price - price  / 100 * this.value  ;
    }
}