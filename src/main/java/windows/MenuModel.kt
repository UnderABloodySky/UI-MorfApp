package windows

import discount.*
import exception.EmptyFieldsException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import restaurant.Restaurant
import searcher.CriteriaById

@Observable
class MenuModel(restaurantModel: RestaurantModel) {

    var code: Int = 0
    var name: String = ""
    var description: String = ""
    var productsOfMenu: MutableList<ProductModel> = mutableListOf()
    var price: Double? = restaurantModel.getPriceOfMenuWithCode(code)
    var menu = restaurantModel.restaurant!!.menus[code]
    var totalWithDiscount: Double = this.menu!!.costAutocalculation()
    var discounts: MutableList<DiscountModel> = transformListOfDiscountToDiscountModel(mutableListOf(
                                                                        FixedDiscount(100.0),
                                                                        PercentageDiscount(20.0),
                                                                        NoDiscount()))
    var discount = getDiscountModelFromDiscount()
    var discountValue = getDiscountModelFromDiscount().value
    var enabled: ObservableBoolean = Enabled()
    var enabledName: String = enabled.optionName
    var selectedProductToAdd: ProductModel? = null
    var selectedProductToRemove: ProductModel? = null
    var restaurantModel = restaurantModel
    var availableProducts = restaurantModel.transformToProductModel()
    var observableBooleans = mutableListOf<ObservableBoolean>(Enabled(), Disabled())

    fun anyOfThisIsEmpty(menuName:String,menuDescription:String):Boolean{

        return  menuName== "" || menuDescription==""
    }

    fun enabledNameStr() = if (enabled.getValue) "Si" else "No"

    fun save() {
        if (this.anyOfThisIsEmpty(this.name, this.description)) {
            throw EmptyFieldsException("Los campos de entrada no pueden estar vacios.")
        }

        else {
            this.restaurantModel.restaurant?.createMenu(this.name,
                    this.description,
                    this.restaurantModel
                            .transformListOfProductModelToProduct(this.productsOfMenu),
                    this.restaurantModel.restaurant as Restaurant,
                    this.discount!!.discount,
                    this.enabled.getValue)
        }
    }

    fun edit() {
        if (this.anyOfThisIsEmpty(this.name, this.description)) {
             throw EmptyFieldsException("Los campos de entrada no pueden estar vacios.")
        }
        else {
            this.restaurantModel.restaurant?.editMenu(this.code,
                    this.name,
                    this.description,
                    this.restaurantModel.transformListOfProductModelToProduct(this.productsOfMenu),
                    this.discount!!.discount,
                    this.enabled.getValue)
        }
    }

    fun delete() = this.restaurantModel.restaurant?.deleteMenu(this.code)

    fun addToListOfProducts(){

        val tempProduct = this.restaurantModel.restaurant?.findProduct(CriteriaById(this.selectedProductToAdd?.code))?.first()
        val tempMenu = this.restaurantModel.restaurant?.findMenu(CriteriaById(this.code))?.first()
        tempMenu?.productsOfMenu?.add(tempProduct!!)

        this.productsOfMenu = this.restaurantModel.transformListOfProductsToModel(tempMenu?.productsOfMenu)

    }
    fun deleteFromListOfProducts(){

        val tempProduct = this.restaurantModel.restaurant?.findProduct(CriteriaById(this.selectedProductToRemove?.code))?.first()
        val tempMenu = this.restaurantModel.restaurant?.findMenu(CriteriaById(this.code))?.first()
        tempMenu?.productsOfMenu?.remove(tempProduct!!)

        this.productsOfMenu = this.restaurantModel.transformListOfProductsToModel(tempMenu?.productsOfMenu)

    }

    fun transformListOfDiscountToDiscountModel(discountList: MutableList<Discount>): MutableList<DiscountModel>{
        var tempDiscountList = mutableListOf<DiscountModel>()
        discountList.forEach { var tempDiscount = DiscountModel(it)
            tempDiscountList.add(tempDiscount)
        }
        return tempDiscountList
    }

    fun noProductSelected(message: String){
        throw UserException (message)
    }

    private fun getDiscountModelFromDiscount(): DiscountModel {
        var tempDiscountModel = DiscountModel(NoDiscount())
        if (this.code != 0){
            val tempDiscount = this.restaurantModel.restaurant?.findMenu(CriteriaById(this.code))?.first()?.discount
            tempDiscountModel = this.discounts.filter{ discount -> tempDiscount?.name == discount.name }.first()}
        return tempDiscountModel

    }

}