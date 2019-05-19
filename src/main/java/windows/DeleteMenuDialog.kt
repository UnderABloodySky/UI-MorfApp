package windows

import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeleteMenuDialog: Dialog<MenuModel> {
    constructor(owner: WindowOwner, model: MenuModel?) : super(owner, model)
    override fun addActions(actions: Panel) {}

    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel).setText("¿Desea borrar el menú " + this.modelObject.name + "?")
                .setFontSize(20)

        Button(mainPanel)
                .setCaption("Aceptar")
                .onClick {
                    this.delete()
                    this.close()
                    this.accept()}

        Button(mainPanel)
                .setCaption("Cancelar")
                .onClick {this.close()}
    }

    fun delete(){
        modelObject.delete()
    }
}