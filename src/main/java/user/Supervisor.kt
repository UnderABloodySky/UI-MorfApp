package user
import restaurant.*
import productAndMenu.*
import applicationModel.*
import java.util.*

class Supervisor( id: Int, val restaurant: Restaurant, password: String, applicationModel: ApplicationModel):
        User ( id, password, applicationModel){


    fun addProductToRestaurantStock(newProduct:Product){
        restaurant.addProductToStock (newProduct);

    }

    fun addMenuToRestaurant(newMenu: Menu){
        restaurant.addMenu(newMenu);
    }

}
