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

        Label(mainPanel).setText("Menu $this.modelObject.name Deleted.")
                        .setWidth(150)

    }

    fun delete(){
        modelObject.delete();
    }
}