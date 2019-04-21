package windows

import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.MainWindow
import user.User

class LoginWindow: MainWindow<UserModel>(UserModel()) {
    override fun createContents(panel: Panel) {

        this.title = "Login MorfApp"
        Label(panel).setText("Ingrese el usuario")
        TextBox(panel)

        Label(panel).setText("Ingrese la contraseña")
        PasswordField(panel)

        Button(panel).setCaption("Ingresar").onClick(modelObject.autenticate())
    }


}