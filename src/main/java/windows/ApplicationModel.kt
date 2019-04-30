package windows

import applicationModel.MorfApp
import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant
import searcher.CriteriaByIdAndString

@Observable
class ApplicationModel(restaurantModel: RestaurantModel) {

    var restaurantModel = restaurantModel;
    var productFilter = "";
    var menuFilter = "";
    var products = restaurantModel.transformToProductModel();
    var menus    = restaurantModel.transformToMenuModel();
    var selectedProduct: ProductModel? = null;
    var selectedMenu: MenuModel? = null;

    fun updateProductList() {
        var tempFoundProducts =  this.restaurantModel
                .restaurant?.findProduct(CriteriaByIdAndString(productFilter)) as MutableList<Product>;

        this.products = this.restaurantModel.transformListOfProductsToModel(tempFoundProducts);
    }
    fun updateMenuList() {
        var tempFoundMenus =  this.restaurantModel
                .restaurant?.findMenu(CriteriaByIdAndString(menuFilter)) as MutableList<Menu>;

        this.menus = this.restaurantModel.transformListOfMenusToMenuModels(tempFoundMenus);
    }
}