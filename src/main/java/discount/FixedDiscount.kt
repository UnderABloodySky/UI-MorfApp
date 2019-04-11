package discount

import exception.IncorrectDiscountValueException

class FixedDiscount(name: String, value : Double ): Discount(name, value) {

    override fun discount(price: Double): Double {
        var localValue = price - this.value;
        if ((localValue) <= 0)
            IncorrectDiscountValueException("The discount can't make the price lesser than 0"); //Chequear como mostrarlo en la vista
        return localValue;
    }
}