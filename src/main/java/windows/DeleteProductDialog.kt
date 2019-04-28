package windows

import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeleteProductDialog: Dialog<ProductModel> {
    constructor(owner: WindowOwner, model: ProductModel?) : super(owner, model)
    override fun addActions(actions: Panel) {
        Button(actions)
                .setCaption("Accept")
                .onClick {  this.close();
                            this.delete();
                            var applicationModel = ApplicationModel(modelObject.restaurantModel);
                            ApplicationWindow(this, applicationModel).open()};

    }
    override fun createFormPanel(mainPanel: Panel) {
        mainPanel.setWidth(100);
        Label(mainPanel).setText(this.modelObject.name + " Deleted.")
    }

    fun delete(){
        modelObject.delete();
    }
}