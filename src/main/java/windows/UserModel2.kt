package windows


import org.uqbar.commons.model.annotations.Observable
import user.User
import applicationModel.ApplicationModel
import exception.NoUserFoundException

@Observable
class UserModel2 {
    var user: String = "...";
    var password: String = "...";
    var autenticated:Boolean = false;

    fun autenticate1() {

        var applicationModel: ApplicationModel = ApplicationModel

        var user: User? = applicationModel.findUser(user)
        if (user != null && user.isCorrectPassword(password)) {

            ApplicationWindow(windows.ApplicationModel()).open()

        } else {

            NoUserFoundException ("No es correcto el usuario / contraseña")
        }
    }
}

