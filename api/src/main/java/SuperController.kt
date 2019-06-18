package api

import api.UserNameInUseException
import applicationModel.MorfApp
import com.fasterxml.jackson.annotation.JsonIgnore
import exception.NoUserAuthenticateException
import geoclase.Geo
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.javalin.NotFoundResponse
import order.Order
import org.eclipse.jetty.http.HttpStatus.CREATED_201
import org.eclipse.jetty.http.HttpStatus.NO_CONTENT_204
import productAndMenu.Menu
import user.Client
import restaurant.Restaurant

class SuperController{
    private var userController = UserController()
    private var restaurantController = RestaurantController()
    private var orderController = OrderController()
    private var menuController = MenuControllerContext()


    fun changeUserController(controller : UserController){
        userController = controller
    }

    fun changeRestaurantController(controller : RestaurantController){
        restaurantController = controller
    }

    fun changeOrderController(controller : OrderController){
        orderController = controller
    }

    fun changeMenuController(controller : MenuControllerContext){
        menuController = controller
    }

    fun addDataMenu(menu : Menu){
        menuController.addDataMenu(menu)
    }
    fun addModelProduct(prod : productAndMenu.Product){
        menuController.addModelProduct(prod)
    }


    fun getMenus(ctx : Context){
        menuController.getMenus(ctx)
    }

    fun getAllProducts(ctx: Context){
        menuController.getAll(ctx)
    }

    fun addDataUser(client : Client){
        userController.addDataUser(client)
    }

    fun login(ctx : Context){
        userController.login(ctx)
    }

    fun getAllUsers(ctx : Context){
        userController.getAllUsers(ctx)
    }

    fun findUser(ctx : Context){
        userController.findUser2(ctx)
    }

    fun updateUser(ctx : Context){
        userController.updateUser(ctx)
    }

    fun deleteUser(ctx : Context){
        userController.deleteUser(ctx)
    }

    fun findUserByMail(ctx : Context){
        userController.findUserByMail(ctx)
    }

    fun addUserByForm(ctx : Context){
        userController.addUserByForm(ctx)
    }

    fun addUser(ctx : Context){
        userController.addUser(ctx)
    }

    fun getAllMenus(ctx : Context){
        restaurantController.getAllMenus(ctx)
    }

    fun addDataRestaurant(restaurant : Restaurant){
        restaurantController.addDataRestaurant(restaurant)
    }
    fun getRestaurant(ctx : Context){
        restaurantController.getRestaurant(ctx)
    }

    fun getAllRestaurants(ctx : Context){
        restaurantController.getAllRestaurants(ctx)
    }

    fun getRestaurantsAndMenusByCriteria(ctx : Context){
        restaurantController.getRestaurantsAndMenusByCriteria(ctx)
    }

    fun historicOrders(ctx : Context){
        orderController.historicOrders(ctx)
    }

    fun pendingOrders(ctx : Context){
        orderController.pendingOrders(ctx)
    }
    fun addOrder(ctx : Context){
        orderController.addOrder(ctx)
    }
/*
    fun getHistoricOrder(ctx : Context){
        orderController.getHistoricOrder(ctx)
    }
    fun getPendingOrder(ctx : Context){
        orderController.getPendingOrder(ctx)
    }
*/
    fun rateAnOrder(ctx : Context){
        orderController.rateAnOrder(ctx)
    }

}
