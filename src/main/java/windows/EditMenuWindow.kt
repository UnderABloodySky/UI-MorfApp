package windows

import com.sun.org.apache.bcel.internal.generic.Select
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Observable

class EditMenuWindow(owner: WindowOwner, model: MenuModel?) : SimpleWindow<MenuModel>(owner, model) {
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {
        title = "Restaurant :: Editar Menu"

        Label(panel)
                .setText("Editar Menu")
                .setFontSize(30)
                .alignCenter()

        this.setTextBoxPanel(panel)
        this.setFourColumnPanel(panel)

        var labelsColumnPanel = Panel(panel).setLayout(ColumnLayout(2))
        Label(labelsColumnPanel)
                .setText("Disponible")
                .setFontSize(20)
                .alignLeft()
        Label(labelsColumnPanel)
                .setText("Agregado Al Menu")
                .setFontSize(20)
                .alignRight()

        var threeColumnPanel = Panel(panel).setLayout(ColumnLayout(3))

        val allProductsTable = List<ProductModel>(threeColumnPanel)
        allProductsTable.setWidth(300)
        allProductsTable.setHeight(89)
        allProductsTable.bindItemsToProperty("availableProducts")
                .adaptWith(ProductModel::class.java, "nameAndPrice")
        allProductsTable.bindValueToProperty<ProductModel, ControlBuilder>("selectedProductToAdd")

        var addRemovePanel = Panel(threeColumnPanel)

        Button(addRemovePanel)
                .setCaption(">>")
                .onClick { if (modelObject.selectedProductToAdd != null)
                            {this.addToListOfProducts()}
                            else {modelObject.noProductSelected(
                            "Debe tener seleccionado un item de la lista de productos disponibles")}
                         }

        Button(addRemovePanel)
                .setCaption("<<")
                .onClick { if (modelObject.selectedProductToRemove != null)
                            {this.removeFromListOfProducts()}
                            else{modelObject.noProductSelected(
                            "Debe tener seleccionado un item de la lista de productos agregados al menu")}
                         }

        val filteredProductsTable = List<ProductModel>(threeColumnPanel)
        filteredProductsTable.setWidth(300)
        filteredProductsTable.setHeight(89)
        filteredProductsTable.bindItems(ObservableProperty<ProductModel>("productsOfMenu"))
                .adaptWith(ProductModel::class.java, "nameAndPrice")
        filteredProductsTable.bindValueToProperty<ProductModel, ControlBuilder>("selectedProductToRemove")

        Button(panel)
                .setCaption("Aceptar")
                .onClick {
                    this.edit()
                    this.close()
                    var applicationModel = ApplicationModel(modelObject.restaurantModel)
                    ApplicationWindow(this, applicationModel).open() }
        Button(panel)
                .setCaption("Cancelar")
                .onClick {
                    this.close()
                    var applicationModel = ApplicationModel(modelObject.restaurantModel)
                    ApplicationWindow(this, applicationModel).open()
                }

    }

    private fun setTextBoxPanel(panel: Panel) {

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2))

        Label(columnPanel).setText("Codigo")
        val codeTextBox = TextBox(columnPanel)
        codeTextBox.setWidth(150)
        codeTextBox.bindValueToProperty<Int, ControlBuilder>("code")

        Label(columnPanel).setText("Nombre")
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("name")

        Label(columnPanel).setText("Descripcion")
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("description")

        Label(columnPanel)
                .setText("Habilitado")
        var enabledSelector = RadioSelector<ObservableBoolean>(columnPanel)
        enabledSelector.bindItemsToProperty("observableBooleans")
                .adaptWith(ObservableBoolean::class.java, "optionName")
        enabledSelector.bindValueToProperty<Boolean,ControlBuilder>("enabled")

    }

    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4))

        Label(fourColumnPanel)
                .setText("Tipo")
        val discountSelector = Selector<DiscountModel>(fourColumnPanel)
        discountSelector.bindValueToProperty<DiscountModel, ControlBuilder>("discount")
        discountSelector.bindItemsToProperty("discounts")
                .adaptWith(DiscountModel::class.java,"name")


        Label(fourColumnPanel)
                .setText("Descuento")
        var discountInput = TextBox(fourColumnPanel)
        discountInput.setWidth(100)
        discountInput.bindValueToProperty<MenuModel, ControlBuilder>("discountValue")
    }

    private fun edit() = modelObject.edit()

    private fun removeFromListOfProducts() =  modelObject.deleteFromListOfProducts()

    private fun addToListOfProducts() = modelObject.addToListOfProducts()
}