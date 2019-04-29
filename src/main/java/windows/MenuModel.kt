package windows

import discount.*
import org.uqbar.commons.model.annotations.Observable

@Observable
class MenuModel(restaurantModel: RestaurantModel) {

    var code = 0;
    var name = "";
    var description = "";
    var productsOfMenu = mutableListOf<ProductModel>();
    var price = 0.0;
    var discount = NoDiscount();
    var discounts: MutableList<Discount> = mutableListOf(FixedDiscount(100.0), PercentageDiscount(20.0), NoDiscount());
    var enabled: Boolean = true;
    var restaurantModel = restaurantModel;
    var currentTotal:Double= 0.00

    fun edit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}