package windows

import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Menu
import productAndMenu.Product
import searcher.CriteriaByIdAndString
import java.lang.NullPointerException

@Observable
class ApplicationModel(restaurantModel: RestaurantModel) {

    var restaurantModel = restaurantModel
    var productFilter : Any? = ""
    var menuFilter : Any? = ""
    var products = restaurantModel.transformToProductModel()
    var menus    = restaurantModel.transformToMenuModel()
    var selectedProduct: ProductModel? = null
    var selectedMenu: MenuModel? = null

    fun updateProductList() {
        try {
            var tempFoundProducts = this.restaurantModel
                    .restaurant?.findProduct(CriteriaByIdAndString(productFilter)) as MutableList<Product>
            this.products = this.restaurantModel.transformListOfProductsToModel(tempFoundProducts)
            resetFilters()
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
            resetFilters()
        }
        catch (e : NullPointerException){
            throw NoIDException("ID no registrado")
        }

    }

    private fun resetFilters(){
        menuFilter = ""
        productFilter = ""
    }


}