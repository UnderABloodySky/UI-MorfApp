package discount

import exception.IncorrectDiscountValueException

class PercentageDiscount(value : Double): Discount("DescuentoPorPorcentaje", value) {
    override fun discount(price: Double): Double {
        if (this.value <= 0 && this.value > 100)
            IncorrectDiscountValueException("El valor del descuento debe ser entre 1 y 100")
        return price / 100 * this.value
    }
}