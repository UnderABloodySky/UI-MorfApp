package windows

import exception.EmptyFieldsException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import restaurant.Restaurant
import searcher.CriteriaById
import java.lang.Exception

@Observable
class MenuModel(restaurantModel: RestaurantModel) {
    var code: Int = 0
    var name: String = ""
    var description: String = ""
    var productsOfMenu: MutableList<ProductModel> = mutableListOf()
    var price: Double? = restaurantModel.getPriceOfMenuWithCode(code)
    var menu = restaurantModel.restaurant?.findMenu(CriteriaById(this.code))
    var totalWithDiscount: Double = 0.0
    var discounts: MutableList<DiscountModel> = restaurantModel.availableDiscounts
    var discount: DiscountModel = restaurantModel.availableDiscounts.first()
    var discountValue: Double = 0.0
    var enabled: ObservableBoolean = Enabled()
    var enabledName: String = enabled.optionName
    var selectedProductToAdd: ProductModel? = null
    var selectedProductToRemove: ProductModel? = null
    var restaurantModel = restaurantModel
    var availableProducts = restaurantModel.transformToProductModel()
    var observableBooleans = mutableListOf<ObservableBoolean>(Enabled(), Disabled())
    var newMenu = true

    fun anyOfThisIsEmpty(menuName:String,menuDescription:String):Boolean{

        return  menuName== "" || menuDescription==""
    }

    fun enabledNameStr() = if (enabled.getValue) "Si" else "No"

    fun save(): MenuModel {

            this.restaurantModel.restaurant?.createMenu(this.name,
                    this.description,
                    this.restaurantModel
                            .transformListOfProductModelToProduct(this.productsOfMenu),
                    this.restaurantModel.restaurant as Restaurant,
                    this.discount!!.discount,
                    this.enabled.getValue)

            var tempMenuModel = this.restaurantModel.transformToMenuModel()!!.last()
            tempMenuModel.newMenu = true //New Menu
            return tempMenuModel
    }
    fun edit() {
        if (this.anyOfThisIsEmpty(this.name, this.description)) {
             throw EmptyFieldsException("Los campos de entrada no pueden estar vacios.")
        }
        else {
            this.discount.discount.value = this.discountValue
            this.restaurantModel.restaurant?.editMenu(this.code,
                    this.name,
                    this.description,
                    this.restaurantModel.transformListOfProductModelToProduct(this.productsOfMenu),
                    this.discount.discount,
                    this.enabled.getValue)
        }
    }

    fun delete() = this.restaurantModel.restaurant?.deleteMenu(this.code)

    fun addToListOfProducts(){

        val tempProduct = this.restaurantModel.
                                restaurant?.
                                findProduct(CriteriaById(this.selectedProductToAdd?.code))?.
                                first()
        val tempMenu = this.restaurantModel.
                            restaurant?.
                            findMenu(CriteriaById(this.code))?.
                            first()
        tempMenu?.productsOfMenu?.
                    add(tempProduct!!)

        this.productsOfMenu = this.restaurantModel.
                                transformListOfProductsToModel(tempMenu?.productsOfMenu)

    }
    fun deleteFromListOfProducts(){

        val tempProduct = this.restaurantModel.restaurant?.findProduct(CriteriaById(this.selectedProductToRemove?.code))?.first()
        val tempMenu = this.restaurantModel.restaurant?.findMenu(CriteriaById(this.code))?.first()
        tempMenu?.productsOfMenu?.remove(tempProduct!!)

        this.productsOfMenu = this.restaurantModel.transformListOfProductsToModel(tempMenu?.productsOfMenu)

    }

    fun noProductSelected(message: String){
        throw UserException (message)
    }

    fun removeMenu(code: Int) {
        this.restaurantModel.restaurant?.deleteMenu(code)
    }
}