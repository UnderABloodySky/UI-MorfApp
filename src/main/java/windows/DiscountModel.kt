package windows

import discount.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class DiscountModel(discount: Discount) {

    var name: String = discount.name;
    var value: Double = discount.value;
    var discount: Discount = discount;


    fun nameAndValue(): String = "$name "

    //fun nameAndValue(): String = "$name amount $value"

}