package windows

import discount.*
import org.uqbar.commons.model.annotations.Observable
import restaurant.Restaurant

@Observable
class MenuModel(restaurantModel: RestaurantModel) {

    var code = 0;
    var name = "";
    var description = "";
    var productsOfMenu = mutableListOf<ProductModel>();
    var price = 0.0;
    var discount: Discount = NoDiscount();
    var discounts: MutableList<Discount> = mutableListOf(FixedDiscount(100.0), PercentageDiscount(20.0), NoDiscount());
    var enabled: Boolean = true;
    var selectedProduct: ProductModel? = null;
    var restaurantModel = restaurantModel;
    var availableProducts = restaurantModel.transformToProductModel();
    var currentTotal:Double= 0.00

    fun save() {
        this.restaurantModel.restaurant?.createMenu(this.name,
                                                    this.description,
                                                    this.restaurantModel
                                                            .transformListOfProductModelToProduct(this.productsOfMenu),
                                                    this.restaurantModel.restaurant as Restaurant,
                                                    this.discount,
                                                    this.enabled);
    }

    fun edit() {
        this.restaurantModel.restaurant?.editMenu(this.code,
                this.name,
                this.description,
                this.restaurantModel.transformListOfProductModelToProduct(this.productsOfMenu),
                this.discount,
                this.enabled);
    }

    fun delete() {
        this.restaurantModel.restaurant?.deleteMenu(this.code);
    }

}