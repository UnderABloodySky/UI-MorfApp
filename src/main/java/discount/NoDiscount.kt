package discount

class NoDiscount: Discount("SinDescuento", 0.0) {
    override fun discount(price: Double) = value
}