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
    var discount = NoDiscount();
    var discounts: MutableList<Discount> = mutableListOf(FixedDiscount(100.0), PercentageDiscount(20.0), NoDiscount());
    var enabled: Boolean = true;
    var restaurantModel = restaurantModel;
    var currentTotal:Double= 0.00

    fun save() {
        this.restaurantModel.restaurant?.createMenu(this.name,
                                                    this.description,
                                                    mutableListOf<>(),
                                                    this.restaurantModel.restaurant as Restaurant,
                                                    this.discount,
                                                    this.enabled);
    }

    fun edit() {
        this.productsOfMenu
        this.restaurantModel.restaurant?.editMenu(this.code,
                this.name,
                this.description,
                this.restaurantModel.,
                this.discount,
                this.enabled);
    }

    fun delete() {
        this.restaurantModel.restaurant?.deleteMenu(this.code);
    }

}