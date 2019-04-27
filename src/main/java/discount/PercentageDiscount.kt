package discount

import exception.IncorrectDiscountValueException

class PercentageDiscount(value : Double): Discount("PercentageDiscount", value) {
    override fun discount(price: Double): Double {
        if (this.value <= 0 && this.value > 100)
            IncorrectDiscountValueException("The Percentage value must greater between 1 and 100"); //Chequear como mostrarlo en la vista
        return price / 100 * this.value;
    }
}