package windows

import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.MainWindow

class WelcomeWindow(model: UserModel): MainWindow<UserModel>(model) {


    override fun createContents(panel: Panel) {

        panel.setLayout(VerticalLayout());
        this.title = "MorfApp"
        Label(panel)
                .setText("Welcome to MoffApp")
                .setFontSize(20)
                .alignCenter();
        Button(panel)
                .setCaption("Login")
                .onClick {
                    LoginWindow(this,UserModel()).open();
                }
        Button(panel)
                .setCaption("Close Application")
                .onClick {
                            this.close()
                }

    }
}