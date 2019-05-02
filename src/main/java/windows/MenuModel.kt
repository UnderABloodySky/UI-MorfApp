package windows

import applicationModel.MorfApp
import discount.*
import exception.EmptyFieldsException
import org.uqbar.commons.model.annotations.Observable
import restaurant.Restaurant

@Observable
class MenuModel(restaurantModel: RestaurantModel) {

    var code: Int = 0;
    var name: String = "";
    var description: String = "";
    var productsOfMenu: MutableList<ProductModel> = mutableListOf();
    var price: Double? = restaurantModel.getPriceOfMenuWithCode(code);
    var menu = restaurantModel.restaurant!!.menus[code]
    var totalWithDiscount: Double = this.menu!!.costAutocalculation()
    var discounts: MutableList<DiscountModel> = transformListOfDiscountToDiscountModel(mutableListOf(
                                                                        FixedDiscount(100.0),
                                                                        PercentageDiscount(20.0),
                                                                        NoDiscount()));
    var discount: DiscountModel = DiscountModel(menu!!.discount);
    var discountName : String = discount.name;
    var enabled: ObservableBoolean = Enabled();
    var enabledName: Boolean = enabled.getValue;
    var selectedProductToAdd: ProductModel? = null;
    var selectedProductToRemove: ProductModel? = null;
    var restaurantModel = restaurantModel;
    var availableProducts = restaurantModel.transformToProductModel();
    var observableNull = null;
    var observableBooleans = mutableListOf<ObservableBoolean>(Enabled(), Disabled())
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
                    this.discount!!.discount,
                    this.enabled.getValue);
        }
    }

    fun edit() {
        if (this.anyOfThisIsEmpty(this.name, this.description)) {
             throw EmptyFieldsException("Fields can't be empty")
        }

        else {
            this.restaurantModel.restaurant?.editMenu(this.code,
                    this.name,
                    this.description,
                    this.restaurantModel.transformListOfProductModelToProduct(this.productsOfMenu),
                    this.discount!!.discount,
                    this.enabled.getValue);
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

    fun transformListOfDiscountToDiscountModel(discountList: MutableList<Discount>): MutableList<DiscountModel>{
        var tempDiscountList = mutableListOf<DiscountModel>();
        discountList.forEach { var tempDiscount = DiscountModel(it);
            tempDiscountList.add(tempDiscount);
        }
        return tempDiscountList;
    }

}