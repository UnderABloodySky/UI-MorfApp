package windows

import org.uqbar.commons.model.annotations.Observable

@Observable
class MenuModel(restaurantModel: RestaurantModel) {

    var code = 0;
    var name = "";
    var description = "";
    var products = mutableListOf<ProductModel>();
    var price = 0.0;
    var enabled: Boolean = true;
    var restaurantModel = restaurantModel;
    var selectedProduct:ProductModel? = null
}