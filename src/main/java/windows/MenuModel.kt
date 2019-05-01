package windows

import discount.*
import exception.EmptyFieldsException
import org.uqbar.commons.model.annotations.Observable
import restaurant.Restaurant

@Observable
class MenuModel(restaurantModel: RestaurantModel) {

    var code: Int = 0;
    var name: String = "";
    var description: String = "";
    var productsOfMenu: MutableList<ProductModel> = mutableListOf<ProductModel>();
    var price: Double = 0.0;
    var discount: Discount = NoDiscount();
    var discounts: MutableList<Discount> = mutableListOf(FixedDiscount(100.0), PercentageDiscount(20.0), NoDiscount());
    var enabled: Boolean = true;
    var selectedProductToAdd: ProductModel? = null;
    var selectedProductToRemove: ProductModel? = null;
    var restaurantModel = restaurantModel;
    var availableProducts = restaurantModel.transformToProductModel();
    var currentTotal:Double= 0.00
    var observableNull = null;

    fun anyOfThisIsEmpty(menuName:String,menuDescription:String):Boolean{

        return  menuName== "" || menuDescription==""
    }

    fun save() {
        if (this.anyOfThisIsEmpty(this.name, this.description)) {
            throw EmptyFieldsException("Fields cant be empty")
        }

        else {
            this.restaurantModel.restaurant?.createMenu(this.name,
                    this.description,
                    this.restaurantModel
                            .transformListOfProductModelToProduct(this.productsOfMenu),
                    this.restaurantModel.restaurant as Restaurant,
                    this.discount,
                    this.enabled);
        }
    }

    fun edit() {
        if (this.anyOfThisIsEmpty(this.name, this.description)) {
             throw EmptyFieldsException("Fields cant be empty")
        }

        else {
            this.restaurantModel.restaurant?.editMenu(this.code,
                      this.name,
                    this.description,
                    this.restaurantModel.transformListOfProductModelToProduct(this.productsOfMenu),
                    this.discount,
                    this.enabled);
        }
    }

    fun delete() {
        this.restaurantModel.restaurant?.deleteMenu(this.code);
    }

    fun addToListOfProducts(){
        this.productsOfMenu.add(this.selectedProductToAdd!!);
    }
    fun deleteFromListOfProducts(){
        this.productsOfMenu.remove(selectedProductToRemove);

    }

}