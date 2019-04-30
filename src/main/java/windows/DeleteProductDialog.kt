package windows

import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeleteProductDialog: Dialog<ProductModel> {
    constructor(owner: WindowOwner, model: ProductModel?) : super(owner, model)
    override fun addActions(actions: Panel) {}

    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel).setText("Product: " + modelObject.name + " Deleted.")
                        .setFontSize(20)
                        .setWidth(400)

        var acceptButton = Button(mainPanel)
                .setCaption("Accept")
                .onClick {  this.close();
                    this.delete();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()};
        acceptButton.alignCenter();

        var cancelButton = Button(mainPanel)
                .setCaption(" Cancel ")
                .onClick {  this.close()
                    var applicationModel = ApplicationModel(modelObject.restaurantModel)
                    ApplicationWindow(this, applicationModel).open()};
        cancelButton.alignCenter();

    }

    fun delete(){
        modelObject.delete();
    }
}