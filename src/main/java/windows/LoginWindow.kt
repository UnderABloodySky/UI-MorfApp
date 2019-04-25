package windows

import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.ControlBuilder
import user.User

class LoginWindow(model: UserModel): MainWindow<UserModel>(UserModel()) {
    override fun createContents(panel: Panel) {
        panel.setLayout(HorizontalLayout());

        this.title = "Login MorfApp"
        Label(panel).setText("Ingrese el usuario")
        TextBox(panel).bindValueToProperty<String,ControlBuilder>("user")

        Label(panel).setText("Ingrese la contraseña")
        PasswordField(panel).bindValueToProperty<String,ControlBuilder>("password")

        Button(panel).setCaption("Ingresar")//.onClick(modelObject.autenticate())
    }


}