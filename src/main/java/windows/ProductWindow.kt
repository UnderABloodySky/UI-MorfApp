package windows

import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Observable
import productAndMenu.Category


class ProductWindow(owner: WindowOwner, model: ProductModel?) : SimpleWindow<ProductModel>(owner, model) {
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {
        title = "Restaurant :: ${modelObject.restaurantModel.name} :: Administración De Producto"

        Label(panel)
                .setText("Administración De Producto")
                .setFontSize(30)
                .alignCenter()

        this.setTextBoxPanel(panel)
        this.setFourColumnPanel(panel)

        Button(panel)
                .setCaption("Aceptar")
                .onClick {  this.edit()
                            this.close()
                            var applicationModel = ApplicationModel(modelObject.restaurantModel)
                            ApplicationWindow(this, applicationModel).open()}
        Button(panel)
                .setCaption("Cancelar")
                .onClick {  this.close()
                            if (modelObject.newProduct)
                                this.removeProduct(modelObject.code)
                            var applicationModel = ApplicationModel(modelObject.restaurantModel)
                            ApplicationWindow(this, applicationModel).open()}
    }

    private fun setTextBoxPanel(panel : Panel){
        val elementCode: Observable<Any> = NotNullObservable("observableNull")

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2)).setWidth(100)

        Label(columnPanel).setText("Codigo")
        val codeTextBox = Label(columnPanel)
        codeTextBox.setFontSize(20)
        codeTextBox.bindValueToProperty<Int, ControlBuilder>("code")
        codeTextBox.bindEnabled<Any, ControlBuilder>(elementCode)

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
        var priceField = TextBox(fourColumnPanel)
                priceField.bindValueToProperty<Double, ControlBuilder>("price")
                priceField.setWidth(80)

        Label(fourColumnPanel)
                .setText("Categoria")
        val categorySelector = Selector<Category>(fourColumnPanel)
        categorySelector.bindValueToProperty<Category, ControlBuilder>("category")
        categorySelector.bindItemsToProperty("categories")

    }

    private fun removeProduct(code: Int) = modelObject.removeProduct(code)

    private fun edit() = modelObject.edit()


}