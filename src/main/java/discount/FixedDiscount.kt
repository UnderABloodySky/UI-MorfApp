package discount

import exception.IncorrectDiscountValueException

class FixedDiscount(value : Double ): Discount("DescuentoPorMonto", value) {

    override fun discount(price: Double): Double {
        if ((price - this.value) <= 0)
            IncorrectDiscountValueException("El descuento no puede hacer el precio menor a cero.")
        return value
    }
}