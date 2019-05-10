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

    fun anyOfThisIsEmpty(productName:String,produtDescription:String,price:Double):Boolean{

        return  productName== "" || produtDescription==""|| price == null
    }


    fun save() {
        if (this.anyOfThisIsEmpty(this.name, this.description,this.price)) {
                throw EmptyFieldsException("Los campos de entrada no pueden estar vacios.")
            }
        else {
                this.restaurantModel.restaurant?.createProduct(this.name, this.description, this.price, this.category)
        }
    }

    fun validatePositiveNumber(price:Double):Boolean{
        return price <0

    }
    fun edit() {
        if (this.anyOfThisIsEmpty(this.name, this.description,this.price)) {
            throw EmptyFieldsException("Los campos de entrada no pueden estar vacios.")
            }

        else {
           if(this.validatePositiveNumber(this.price))  {
               throw UserException("El precio debe ser un valor positivo.")}

           else {
                 this.restaurantModel.restaurant?.removeProductsFromMenus(this.code)
                 this.restaurantModel.restaurant?.editProduct(this.code, this.name, this.description, this.price, this.category)
            }
        }
    }

    fun delete() {
        this.restaurantModel.restaurant?.removeProductsFromMenus(this.code)
        this.restaurantModel.restaurant?.deleteProduct(this.code)
    }

    fun nameAndPrice():String = "$name    $$price"

}