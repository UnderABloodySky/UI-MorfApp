package windows

import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import productAndMenu.Menu
import productAndMenu.Product
import searcher.CriteriaById
import searcher.CriteriaByIdAndString

@Observable
class ApplicationModel(restaurantModel: RestaurantModel) {

    var restaurantModel = restaurantModel
    var productFilter : String? = null
    var productIdFilter : Int? = null
    var menuFilter : String? = null
    var menuIdFilter : Int? = null
    var products = restaurantModel.transformToProductModel()
    var menus    = restaurantModel.transformToMenuModel()
    var selectedProduct: ProductModel? = null
    var selectedMenu: MenuModel? = null

    fun updateProductList() {
        var tempFoundProducts = this.restaurantModel
                .restaurant?.findProduct(CriteriaByIdAndString(productFilter)) as MutableList<Product>

        this.products = this.restaurantModel.transformListOfProductsToModel(tempFoundProducts)
    }

    fun updateProductListId() {
        try {
            var tempFoundProducts = this.restaurantModel
                    .restaurant?.findProduct(CriteriaById(productIdFilter)) as MutableList<Product>

            this.products = this.restaurantModel.transformListOfProductsToModel(tempFoundProducts)
        }
        catch(e : NullPointerException){
            this.productIdFilter = null
            throw UserException("ID Incorrecto")
        }
    }

    fun updateMenuList() {
        var tempFoundMenus =  this.restaurantModel
                .restaurant?.findMenu(CriteriaByIdAndString(menuFilter)) as MutableList<Menu>

        this.menus = this.restaurantModel.transformListOfMenusToMenuModels(tempFoundMenus)
    }

}