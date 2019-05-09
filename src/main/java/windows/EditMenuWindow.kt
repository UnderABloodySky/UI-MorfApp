package windows

import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
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
                .setText("Seleccionado")
                .setFontSize(20)
                .alignRight()

        var listOfProductsColumnPanel = Panel(panel).setLayout(ColumnLayout(3))
        val allProductsSelector = List<ProductModel>(listOfProductsColumnPanel)
        allProductsSelector.bindItemsToProperty("availableProducts")
                .adaptWith(ProductModel::class.java,"nameAndPrice")
        allProductsSelector.bindValueToProperty<ProductModel, ControlBuilder>("selectedProductToAdd")

        var addRemoveButtonPanel = Panel(listOfProductsColumnPanel).setLayout(VerticalLayout())
        Button(addRemoveButtonPanel)
                .setCaption(">>")
                .onClick {
                    this.addToListOfProducts()
                    val newMenuWindow = EditMenuWindow(this, modelObject)
                    //newMenuWindow.open();
                    }
        Button(addRemoveButtonPanel)
                .setCaption("<<")
                .onClick {
                    this.close()
                    this.removeFromListOfProducts()
                    val newMenuWindow = EditMenuWindow(this, modelObject)
                    newMenuWindow.open()}

        val filteredProductsSelector = List<ProductModel>(listOfProductsColumnPanel)
        filteredProductsSelector.bindItemsToProperty("productsOfMenu")
                .adaptWith(ProductModel::class.java,"nameAndPrice")
        filteredProductsSelector.bindValueToProperty<ProductModel, ControlBuilder>("selectedProductToRemove");

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
        val elementCode: Observable<Any> = NotNullObservable("observableNull")

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2))

        Label(columnPanel).setText("Codigo")
        val codeTextBox = TextBox(columnPanel)
        codeTextBox.setWidth(150)
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

        Label(columnPanel)
                .setText("Habilitado")
        val enabledSelector = RadioSelector<ObservableBoolean>(columnPanel)
        enabledSelector.bindValueToProperty<Boolean,ControlBuilder>("enabled")

        enabledSelector.bindItemsToProperty("observableBooleans")
                .adaptWith(ObservableBoolean::class.java, "getValue")
    }

    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4))

        Label(fourColumnPanel)
                .setText("Tipo")
        val discountSelector = Selector<DiscountModel>(fourColumnPanel)
        discountSelector.bindValueToProperty<DiscountModel, ControlBuilder>("discount")
        discountSelector.bindItemsToProperty("discounts")
                .adaptWith(DiscountModel::class.java,"nameAndValue")


        Label(fourColumnPanel)
                .setText("Descuento")
        Label(fourColumnPanel).bindValueToProperty<DiscountModel, ControlBuilder>("discountName")

    }

    private fun edit() = modelObject.edit()

    private fun removeFromListOfProducts() =  modelObject.deleteFromListOfProducts()

    private fun addToListOfProducts() = modelObject.addToListOfProducts()
}