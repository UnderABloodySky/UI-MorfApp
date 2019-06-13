package windows

import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeleteProductDialog: Dialog<ProductInMenusModel> {
    constructor(owner: WindowOwner, model: ProductInMenusModel) : super(owner, model)
    override fun addActions(actions: Panel) {}

    override fun createFormPanel(mainPanel: Panel) {

        Label(mainPanel).setText("¿Desea borrar el producto " + this.modelObject.productName + "?")
                        .setFontSize(20)
        Label(mainPanel)
        Label(mainPanel)
        Label(mainPanel)
                .setText("Tenga en cuenta que se borrará de los siguientes menús:")
                .setFontSize(20)
                .alignCenter()

        var listOfMenusWithTheProduct = org.uqbar.arena.widgets.List<MenuModel>(mainPanel)
        listOfMenusWithTheProduct.bindItemsToProperty("menusOfSelectedProduct")
                .adaptWith(MenuModel::class.java,"name")

        var acceptButton = Button(mainPanel)
                .setCaption("Aceptar")
                .onClick {
                    this.delete()
                    this.close()
                    this.accept()}
        acceptButton.alignCenter()

        var cancelButton = Button(mainPanel)
                .setCaption("Cancelar")
                .onClick { this.close()}
        cancelButton.alignCenter()

    }

    fun delete(){
        modelObject.delete()
    }
}