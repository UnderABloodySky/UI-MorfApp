package windows

import org.uqbar.commons.model.annotations.Observable

@Observable
class ApplicationModel(userModel: UserModel) {

    var productFilter: Any? = null;
    var menuFilter: Any? = null;
    var products  =  userModel.restaurant.products;
    var menus = mutableListOf<MenuModel>();
    var selectedProduct: ProductModel? = null;
    var selectedMenu: MenuModel? = null;

}