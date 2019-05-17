package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder

class MenuWindow(owner: WindowOwner, model: MenuModel?) : SimpleWindow<MenuModel>(owner, model) {
    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {

        title = "MorfApp :: ${modelObject.restaurantModel.name} :: Administración De Menú"

        Label(panel)
                .setText("Administración De Menú")
                .setFontSize(30)
                .alignCenter()

        this.setTextBoxPanel(panel)
        this.setFourColumnPanel(panel)

        var labelsColumnPanel = Panel(panel).setLayout(ColumnLayout(3))
        Label(labelsColumnPanel)
                .setText("Disponible")
                .setFontSize(20)
                .alignLeft()
        Label(labelsColumnPanel)
        Label(labelsColumnPanel)
                .setText("Agregado Al Menu")
                .setFontSize(20)
                .alignRight()

        var threeColumnPanel = Panel(panel).setLayout(ColumnLayout(3))

        val allProductsTable = List<ProductModel>(threeColumnPanel)
        allProductsTable.setWidth(200)
        allProductsTable.setHeight(89)
        allProductsTable.bindItemsToProperty("availableProducts")
                .adaptWith(ProductModel::class.java, "nameAndPrice")
        allProductsTable.bindValueToProperty<ProductModel, ControlBuilder>("selectedProductToAdd")

        var addRemovePanel = Panel(threeColumnPanel)

        var rightButton = Button(addRemovePanel)
                rightButton.setCaption("         >>         ")
                rightButton.setFontSize(20)
                rightButton.onClick { if (modelObject.selectedProductToAdd != null)
                            {this.addToListOfProducts()}
                            else {modelObject.noProductSelected(
                            "Debe tener seleccionado un item de la lista de productos disponibles")}
                         }

        var leftButton = Button(addRemovePanel)
                leftButton.setCaption("         <<         ")
                leftButton.setFontSize(20)
                leftButton.onClick { if (modelObject.selectedProductToRemove != null)
                            {this.removeFromListOfProducts()}
                            else{modelObject.noProductSelected(
                            "Debe tener seleccionado un item de la lista de productos agregados al menu")}
                         }

        val filteredProductsTable = List<ProductModel>(threeColumnPanel)
        filteredProductsTable.setWidth(200)
        filteredProductsTable.setHeight(89)
        filteredProductsTable.bindItemsToProperty("productsOfMenu")
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
                    if (modelObject.newMenu)
                        this.removeMenu(modelObject.code)
                    this.close()
                    var applicationModel = ApplicationModel(modelObject.restaurantModel)
                    ApplicationWindow(this, applicationModel).open()
                }
    }

    private fun setTextBoxPanel(panel: Panel) {

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2))

        Label(columnPanel).setText("Codigo")
        val codeTextBox = Label(columnPanel)
        codeTextBox.setFontSize(20)
        codeTextBox.bindValueToProperty<Int, ControlBuilder>("code")

        Label(columnPanel).setText("Nombre")
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("name")

        Label(columnPanel).setText("Descripción")
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("description")

        Label(columnPanel)
                .setText("Habilitado")
        val enabledSelector = Selector<ObservableBoolean>(columnPanel)
        enabledSelector.setWidth(160)
        enabledSelector.bindValueToProperty<ObservableBoolean, ControlBuilder>("enabled")
        enabledSelector.bindItemsToProperty("observableBooleans")
                .adaptWith(ObservableBoolean::class.java, "optionName")


    }
    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4))

        Label(fourColumnPanel)
                .setText("Tipo")
        val discountSelector = Selector<DiscountModel>(fourColumnPanel)
        discountSelector.bindValueToProperty<DiscountModel, ControlBuilder>("discount")
        discountSelector.bindItemsToProperty("discounts")
                .adaptWith(DiscountModel::class.java,"description")

        Label(fourColumnPanel)
                .setText("Descuento")
        var discountInput = TextBox(fourColumnPanel)
                discountInput.bindValueToProperty<Double, ControlBuilder>("discountValue")
                discountInput.setWidth(100)
                discountInput.withFilter { event -> event.potentialTextResult.matches(Regex("[0-9]*"))}
    }

    private fun removeMenu(code: Int) = modelObject.removeMenu(code)

    private fun edit() = modelObject.edit()

    private fun removeFromListOfProducts() =  modelObject.deleteFromListOfProducts()

    private fun addToListOfProducts() = modelObject.addToListOfProducts()
}