package windows

import applicationModel.MorfApp
import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Category

@Observable
class ProductModel {

    var code: Int = 0;
    var name: String = "";
    var description: String = "";
    var price: Double = 0.0;
    var category: Category = Category.NONE;
    var categories : MutableList<Category> = Category.values().toMutableList();

    fun save() {


    }


}