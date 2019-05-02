package windows

import discount.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class DiscountModel {

    var name: String = "NoDiscount"
    var value: Double = 0.0
    var discount: Discount = NoDiscount();

    fun nameAndValue(): String = "$name amount $value"

}