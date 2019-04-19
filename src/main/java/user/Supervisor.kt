package user
import restaurant.*
import productAndMenu.*
import applicationModel.*
import paymentMethod.PaymentMethod
import java.util.*

class Supervisor( code : Int, id : String , val restaurant: Restaurant, password: String, applicationModel: ApplicationModel):
        User (code, id, password, applicationModel) {


    fun addProductToRestaurantStock(newProduct: Product) {
        restaurant.addProductToStock(newProduct);

    }

    fun addMenuToRestaurant(newMenu: Menu) {
        restaurant.addMenu(newMenu);
    }


    //aca ver como tirar la excepcion que no lo tnees en el stock.
    fun removeProductFromRestaurantStock(productToRemove: Product) {
        if (restaurant.productIsContainedInTheStock(productToRemove)) {
            restaurant.removeProductFromStock(productToRemove)
        }
    }

    fun removeMenuFromRestaurant(menuToRemove: Menu) {
        if (restaurant.menus.contains(menuToRemove)) {
            restaurant.removeMenu(menuToRemove);
        }
    }


    fun addPaymentMethod(newPaymentMethod: PaymentMethod) {
        restaurant.addPaymentMethod(newPaymentMethod);
    }

    fun removePaymentMethodRestaurant(paymentMethod: PaymentMethod) {
        if (restaurant.availablePaymentMethods.contains(paymentMethod)) {
            restaurant.removePaymentMethod(paymentMethod);
        }

    }
}