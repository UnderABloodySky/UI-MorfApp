package windows

import exception.NoIDException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import productAndMenu.Menu
import productAndMenu.Product
import searcher.CriteriaById
import searcher.CriteriaByIdAndString
import java.lang.NullPointerException

@Observable
class ApplicationModel(restaurantModel: RestaurantModel) {

    var restaurantModel = restaurantModel
    var productFilter : Any? = null
    var menuFilter : Any? = null
    var products = restaurantModel.transformToProductModel()
    var menus    = restaurantModel.transformToMenuModel()
    var selectedProduct: ProductModel? = null
    var selectedMenu: MenuModel? = null

    fun updateProductList() {
        try {
            var tempFoundProducts = this.restaurantModel
                    .restaurant?.findProduct(CriteriaByIdAndString(productFilter)) as MutableList<Product>
            this.products = this.restaurantModel.transformListOfProductsToModel(tempFoundProducts)
        }
        catch(e : NullPointerException){
            throw NoIDException("ID no registrado")
        }
    }


    fun updateMenuList() {
        try {
            var tempFoundMenus = this.restaurantModel
                    .restaurant?.findMenu(CriteriaByIdAndString(menuFilter)) as MutableList<Menu>
            this.menus = this.restaurantModel.transformListOfMenusToMenuModels(tempFoundMenus)
        }
        catch (e : NullPointerException){
            throw NoIDException("ID no registrado")
        }
    }

}