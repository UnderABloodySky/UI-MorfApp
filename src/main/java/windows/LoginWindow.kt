package windows

import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.ControlBuilder

class LoginWindow(model: UserModel): MainWindow<UserModel>(model) {

    override fun createContents(panel: Panel) {

        panel.setLayout(VerticalLayout());

        this.title = "Login MorfApp"

        Label(panel).setText("Ingrese el usuario")
        TextBox(panel)
                .setWidth(250)
                .bindValueToProperty<String,ControlBuilder>("user")

        Label(panel).setText("Ingrese la contraseña")


        PasswordField(panel)
                .setWidth(250)
                .bindValueToProperty<String,ControlBuilder>("password")

        Button(panel)
                .setCaption("Ingresar")
                .onClick {
                    this.close();
                    var loggedUserModel = ApplicationModel(modelObject.autenticate());
                    var applicationWindow = ApplicationWindow(this, loggedUserModel);
                    applicationWindow.open();
                }
    }
}