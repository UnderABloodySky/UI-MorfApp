package windows

import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeleteMenuDialog: Dialog<MenuModel> {
    constructor(owner: WindowOwner, model: MenuModel?) : super(owner, model)
    override fun addActions(actions: Panel) {
        Button(actions)
                .setCaption("Accept")
                .onClick {  this.close();
                    this.delete();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()};

        Button(actions)
                .setCaption(" Cancel ")
                .onClick {  this.close();
                    this.delete();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()};


    }
    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel).setText(this.modelObject.name + " Deleted.")
                .setWidth(150)

    }

    fun delete(){
        modelObject.delete();
    }
}