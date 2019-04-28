package windows

import discount.NoDiscount
import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Menu

@Observable
class MenuModel(restaurantModel: RestaurantModel) {

    var code = 0;
    var name = "";
    var description = "";
    var productsOfMenu = mutableListOf<ProductModel>();
    var price = 0.0;
    var enabled: Boolean = true;
    var restaurantModel = restaurantModel;
    var selectedProduct:ProductModel? = null;
    var currentTotal:Double= 0.00



}