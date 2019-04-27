package windows

import org.uqbar.commons.model.annotations.Observable
import user.User
import applicationModel.MorfApp
import exception.NoUserFoundException
import org.uqbar.arena.windows.WindowOwner
import restaurant.Restaurant
import user.Supervisor

@Observable
class UserModel {
    var user: String = "...";
    var password: String = "...";
    var autenticated:Boolean = false;
    var restaurant: Restaurant? = null;

    //el fin user tiene uqe buscar por nombre de usuario .
    fun autenticate() : UserModel {

        var foundUser: User? = MorfApp.findUser(this.user)
             if (foundUser != null && foundUser.isCorrectPassword(this.password)) {

                 foundUser as Supervisor;
                 this.restaurant = foundUser.restaurant;
                 return this;
             }
             else {throw NoUserFoundException ("No es correcto el usuario / contraseña")}

    }
}

