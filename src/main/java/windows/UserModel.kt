package windows

import org.uqbar.commons.model.annotations.Observable
import user.User
import applicationModel.MorfApp
import exception.NoUserFoundException
import restaurant.Restaurant
import user.Supervisor

@Observable
class UserModel {
    var user: String = "...";
    var password: String = "...";
    var autenticated:Boolean = false;
    var restaurant: Restaurant? = null;

    //el fin user tiene uqe buscar por nombre de usuario .
    fun autenticate() {

        var morfApp:MorfApp = MorfApp;

        var foundUser: User? = morfApp.findUser(this.user)
            print(foundUser?.name);
             if (foundUser != null && foundUser.isCorrectPassword(this.password)) {

                 foundUser as Supervisor;
                 this.restaurant = foundUser.restaurant;
                 ApplicationWindow(ApplicationModel(this)).open();
             }

             else {throw NoUserFoundException ("No es correcto el usuario / contraseña")}

    }
}

