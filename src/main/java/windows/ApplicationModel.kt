package windows

import org.uqbar.commons.model.annotations.Observable


@Observable
class ApplicationModel {
    var productFilter: Any? = null;
    var menuFilter: Any? = null;
    var products = mutableListOf<ProductModel>();

    var selectedProduct: ProductModel? = null;

}