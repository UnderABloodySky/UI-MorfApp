package windows

import applicationModel.MorfApp
import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant

@Observable
class ApplicationModel(userModel: UserModel) {

    var productFilter: Any? = null;
    var menuFilter: Any? = null;
    var products = transformToProductModel(userModel.restaurant)
    var menus    = userModel.restaurant?.menus;
    var selectedProduct: ProductModel? = null;
    var selectedMenu: MenuModel? = null;


    fun transformToProductModel(restaurant : Restaurant?): MutableSet<ProductModel>{
        var productsModel = mutableSetOf<ProductModel>()

        restaurant?.products?.forEach { t, u ->
                                                var tempProduct = ProductModel()
                                                tempProduct.code = t;
                                                tempProduct.name = u.name;
                                                tempProduct.description = u.description;
                                                tempProduct.price = u.price;
                                                tempProduct.category = u.category;
                                                productsModel.add(tempProduct) }

        return productsModel
    }
}