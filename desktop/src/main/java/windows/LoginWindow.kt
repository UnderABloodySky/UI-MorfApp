package windows

import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner

class LoginWindow(owner: WindowOwner, model: UserModel): SimpleWindow <UserModel>(owner,model) {

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {

        panel.setLayout(VerticalLayout())

        this.title = "MorfApp :: Login "

        Label(panel).setText("Usuario")
        TextBox(panel)
                .setWidth(250)
                .bindValueToProperty<String,ControlBuilder>("user")

        Label(panel).setText("Contraseña")

        PasswordField(panel)
                .setWidth(250)
                .bindValueToProperty<String,ControlBuilder>("password")

        Button(panel)
                .setCaption("Ingresar")
                .onClick {

                    var loggedUserModel = ApplicationModel(modelObject.autenticate())
                    var applicationWindow = ApplicationWindow(this, loggedUserModel)
                    this.close()
                    applicationWindow.open()
                }

        Button(panel)
                .setCaption("Salir")
                .onClick { this.close() }
    }
}