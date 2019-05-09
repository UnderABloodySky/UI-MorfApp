package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import productAndMenu.Category


class NewProductWindow(owner: WindowOwner, model: ProductModel) : SimpleWindow<ProductModel>(owner, model) {
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {
        title = "Restaurant :: Nuevo Producto"

        Label(panel)
                .setText("Nuevo Producto")
                .setFontSize(30)
                .alignCenter()

        this.setTextBoxPanel(panel)
        this.setFourColumnPanel(panel)

        Button(panel)
                .setCaption("Aceptar")
                .onClick {  this.save()
                            this.close()
                            var applicationModel = ApplicationModel(modelObject.restaurantModel)
                            ApplicationWindow(this, applicationModel).open()}
        Button(panel)
                .setCaption("Cancelar")
                .onClick {  this.close()
                            var applicationModel = ApplicationModel(modelObject.restaurantModel)
                            ApplicationWindow(this, applicationModel).open()}

    }

    private fun setTextBoxPanel(panel : Panel){

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2)).setWidth(100)

        Label(columnPanel).setText("Nombre")
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("name")

        Label(columnPanel).setText("Descripcion")
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("description")
    }
    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4))
        Label(fourColumnPanel).setText("Precio")
        TextBox(fourColumnPanel)
                .setWidth(80)
                .bindValueToProperty<String, ControlBuilder>("price")

        Label(fourColumnPanel)
                .setText("Categoria")
        val categorySelector = Selector<Category>(fourColumnPanel)
        categorySelector.bindValueToProperty<Category, ControlBuilder>("category")
        categorySelector.bindItemsToProperty("categories")

    }
    private fun save() = modelObject.save()

}