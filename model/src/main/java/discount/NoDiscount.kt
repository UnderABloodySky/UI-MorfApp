package discount

class NoDiscount: Discount("SinDescuento", "Sin Descuento", 0.0) {

    override fun discount(price: Double) = 0.0  
}