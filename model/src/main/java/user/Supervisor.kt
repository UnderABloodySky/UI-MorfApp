package user
import restaurant.*
import productAndMenu.*
import applicationModel.*
import paymentMethod.PaymentMethod

class Supervisor( code : Int, name : String, id : String , val restaurant: Restaurant, password: String, applicationModel: MorfApp):
        User (code, name, id, password, applicationModel) {

    fun addProductToRestaurantStock(newProduct: Product) = restaurant.addProductToStock(newProduct)

    fun addMenuToRestaurant(newMenu: Menu) = restaurant.addMenu(newMenu)

    fun removeProductFromRestaurantStock(productToRemove: Product) {
        if (restaurant.productIsContainedInTheStock(productToRemove)) {
            restaurant.removeProductFromStock(productToRemove)
        }
    }

    fun removeMenuFromRestaurant(menuToRemove: Menu) {
        if (restaurant.menus.containsValue(menuToRemove)) {
            restaurant.removeMenu(menuToRemove)
        }
    }

    fun addPaymentMethod(newPaymentMethod: PaymentMethod) {
        restaurant.addPaymentMethod(newPaymentMethod)
    }

    fun removePaymentMethodRestaurant(paymentMethod: PaymentMethod) {
        if (restaurant.availablePaymentMethods.contains(paymentMethod)) {
            restaurant.removePaymentMethod(paymentMethod)
        }
    }
}