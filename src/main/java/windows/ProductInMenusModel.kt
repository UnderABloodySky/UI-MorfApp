package windows

import org.uqbar.commons.model.annotations.Observable

@Observable
class ProductInMenusModel(var applicationModel: ApplicationModel) {

    var menusOfSelectedProduct= applicationModel.restaurantModel.menusOfProduct(applicationModel.selectedProduct?.code);

}