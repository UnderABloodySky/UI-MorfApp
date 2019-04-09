package discount

class FixedDiscount(name: String, value : Double ): Discount(name, value){

    override fun processDiscount(price: Double): Double {
        //Verificar si price < value?

        return price - this.value
    }
}