package windows

import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.ui.view.ErrorViewer

//ver como implementar la interfaces de errorViewer

class LoginWindow(owner: WindowOwner, model: UserModel): SimpleWindow <UserModel>(owner,model) {

    override fun addActions(p0: Panel?) : Unit {}

    override fun createFormPanel(panel: Panel) {

        panel.setLayout(VerticalLayout());

        this.title = "MorfApp :: Login "

        Label(panel).setText("User")
        TextBox(panel)
                .setWidth(250)
                .bindValueToProperty<String,ControlBuilder>("user")

        Label(panel).setText("Password")


        PasswordField(panel)
                .setWidth(250)
                .bindValueToProperty<String,ControlBuilder>("password")

        Button(panel)
                .setCaption("Login")
                .onClick {

                        var loggedUserModel = ApplicationModel(modelObject.autenticate());
                        var applicationWindow = ApplicationWindow(this, loggedUserModel);
                    this.close();
                    applicationWindow.open();


                }
        Button(panel)
                .setCaption("Exit")
                .onClick {
                        this.close()
                }
    }


}