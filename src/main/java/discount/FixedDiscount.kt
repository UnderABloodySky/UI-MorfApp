package discount

class FixedDiscount(name: String, value : Double ): Discount(name, value){

    override fun discount(price: Double): Double {
        //Verificar si price < value?
        return this.value
    }
}