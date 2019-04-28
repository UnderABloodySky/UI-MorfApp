package windows

import applicationModel.MorfApp
import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant

@Observable
class ApplicationModel(restaurantModel: RestaurantModel) {

    var restaurantModel = restaurantModel;
    var productFilter: Any? = null;
    var menuFilter: Any? = null;
    var products = restaurantModel.transformToProductModel();
    var menus    = restaurantModel.transformToMenuModel();
    var selectedProduct: ProductModel? = null;
    var selectedMenu: MenuModel? = null;

}