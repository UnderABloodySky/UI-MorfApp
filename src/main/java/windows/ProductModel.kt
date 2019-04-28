package windows

import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Category

@Observable
class ProductModel(restaurantModel: RestaurantModel) {

    var code: Int = 0;
    var name: String = "";
    var description: String = "";
    var price: Double = 0.0;
    var category: Category = Category.NONE;
    var categories : MutableList<Category> = Category.values().toMutableList();
    var restaurantModel = restaurantModel;

    fun save() {

        this.restaurantModel.restaurant?.createProduct(this.name, this.description, this.price, this.category);

    }


}