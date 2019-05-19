package windows

import org.uqbar.commons.model.annotations.Observable

@Observable
class ProductInMenusModel(var applicationModel: ApplicationModel) {

    var menusOfSelectedProduct = applicationModel.restaurantModel.menusOfProduct(applicationModel.selectedProduct?.code)
    var productName:String?= applicationModel.selectedProduct?.name
    var code:Int? = applicationModel.selectedProduct?.code
    var description = applicationModel.selectedProduct?.description
    var price = applicationModel.selectedProduct?.price
    var category = applicationModel.selectedProduct?.category
    var restaurantModel = applicationModel.restaurantModel

    fun delete() {
        this.restaurantModel.restaurant?.removeProductsFromMenus(this.code!!)
        this.restaurantModel.restaurant?.deleteProduct(this.code!!)
    }

}