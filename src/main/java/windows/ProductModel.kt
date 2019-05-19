package windows

import exception.EmptyFieldsException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException
import productAndMenu.Category

@Observable
class ProductModel(restaurantModel: RestaurantModel) {

    var code: Int = 0
    var name: String = ""
    var description: String = ""
    var price: Double = 0.0
    var category: Category = Category.NINGUNO
    var categories : MutableList<Category> = Category.values().toMutableList()
    var restaurantModel = restaurantModel
    var observableNull = null
    var newProduct = true

    fun anyOfThisIsEmpty(productName:String,produtDescription:String,price:Double):Boolean{

        return  productName== "" || produtDescription==""|| price == null
    }

    fun save(): ProductModel {
        this.restaurantModel.restaurant?.createProduct(this.name, this.description, this.price, this.category)
        var tempProductModel = this.restaurantModel.transformToProductModel()!!.last()
        tempProductModel.newProduct = true //new product
        return tempProductModel
    }

    fun validatePositiveNumber(price:Double):Boolean{
        return price <0

    }
    fun edit() {
        if (this.anyOfThisIsEmpty(this.name, this.description,this.price))
            throw EmptyFieldsException("Los campos de entrada no pueden estar vacios.")

        else {
           if(this.validatePositiveNumber(this.price))
               throw UserException("El precio debe ser un valor positivo.")
           else
               this.restaurantModel.restaurant?.editProduct(this.code, this.name, this.description, this.price, this.category)
        }
    }

    fun nameAndPrice():String = "$name    $$price"

    fun removeProduct(code: Int) {
        this.restaurantModel.restaurant?.deleteProduct(code)
    }

}