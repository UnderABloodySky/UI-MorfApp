package api

import api.UserNameInUseException
import applicationModel.MorfApp
import com.fasterxml.jackson.annotation.JsonIgnore
import controllers.UserController
import exception.NoUserAuthenticateException
import geoclase.Geo
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.javalin.NotFoundResponse
import order.Order
import org.eclipse.jetty.http.HttpStatus.CREATED_201
import org.eclipse.jetty.http.HttpStatus.NO_CONTENT_204
import user.Client

class SuperController{
    private var userController = UserController()
    private var restaurantController = RestaurantController()
    private var orderController = OrderController()

    fun changeUserController(controller : UserController){
        userController = controller
    }

    fun changeRestaurantController(controller : RestaurantController){
        restaurantController = controller
    }

    fun changeOrderController(controller : OrderController){
        orderController = controller
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

    fun getRestaurantsAndMenusByCriteria(ctx : Context){
        restaurantController.getRestaurantsAndMenusByCriteria(ctx)
    }

    fun allOrders(ctx : Context){
        orderController.allOrders(ctx)
    }

    fun addOrder(ctx : Context){
        orderController.addOrder(ctx)
    }

    fun getOrder(ctx : Context){
        orderController.getOrder(ctx)
    }

    fun rateAnOrder(ctx : Context){
        orderController.rateAnOrder(ctx)
    }

}
