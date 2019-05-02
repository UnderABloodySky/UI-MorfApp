package windows

import discount.Discount
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.bindings.ObservableValue
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.MainWindow
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Observable
import productAndMenu.Category


class EditMenuWindow(owner: WindowOwner, model: MenuModel?) : SimpleWindow<MenuModel>(owner, model) {
    override fun addActions(p0: Panel?): Unit {}

    override fun createFormPanel(panel: Panel) {
        title = "Restaurant :: Edit Menu";

        Label(panel)
                .setText("Edit Menu")
                .setFontSize(30)
                .alignCenter();

        this.setTextBoxPanel(panel);
        this.setFourColumnPanel(panel);

        var labelsColumnPanel = Panel(panel).setLayout(ColumnLayout(2));
        Label(labelsColumnPanel)
                .setText("Available")
                .setFontSize(20)
                .alignLeft()
        Label(labelsColumnPanel)
                .setText("Selected")
                .setFontSize(20)
                .alignRight()

        var listOfProductsColumnPanel = Panel(panel).setLayout(ColumnLayout(3));
        val allProductsSelector = List<ProductModel>(listOfProductsColumnPanel);
        allProductsSelector.bindItemsToProperty("availableProducts")
                .adaptWith(ProductModel::class.java,"nameAndPrice")
        allProductsSelector.bindValueToProperty<ProductModel, ControlBuilder>("selectedProductToAdd")

        var addRemoveButtonPanel = Panel(listOfProductsColumnPanel).setLayout(VerticalLayout());
        Button(addRemoveButtonPanel)
                .setCaption(">>")
                .onClick { this.addToListOfProducts() }
        Button(addRemoveButtonPanel)
                .setCaption("<<")
                .onClick { this.removeFromListOfProducts() }

        val filteredProductsSelector = List<ProductModel>(listOfProductsColumnPanel);
        filteredProductsSelector.bindItemsToProperty("productsOfMenu")
                .adaptWith(ProductModel::class.java,"nameAndPrice")
        filteredProductsSelector.bindValueToProperty<ProductModel, ControlBuilder>("selectedProductToRemove");

        Button(panel)
                .setCaption("Accept")
                .onClick {
                    this.edit();
                    this.close();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()
                };
        Button(panel)
                .setCaption("Cancel")
                .onClick {
                    this.close();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()
                };

    }

    private fun setTextBoxPanel(panel: Panel) {
        val elementCode: Observable<Any> = NotNullObservable("observableNull");

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2));

        Label(columnPanel).setText("Code");
        val codeTextBox = TextBox(columnPanel);
        codeTextBox.setWidth(150)
        codeTextBox.bindValueToProperty<Int, ControlBuilder>("code");
        codeTextBox.bindEnabled<Any, ControlBuilder>(elementCode);

        Label(columnPanel).setText("Name");
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("name");

        Label(columnPanel).setText("Description");
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("description");
    }

    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4));

        Label(fourColumnPanel)
                .setText("Disc. Type");
        val discountSelector = Selector<DiscountModel>(fourColumnPanel);
        discountSelector.bindItemsToProperty("discounts")
                .adaptWith(DiscountModel::class.java,"nameAndValue")
        discountSelector.bindValueToProperty<DiscountModel, ControlBuilder>("discount")

        Label(fourColumnPanel)
                .setText("Discount")
        TextBox(fourColumnPanel).bindValueToProperty<DiscountModel, ControlBuilder>("discount")
                //.adaptWith(DiscountModel::class.java,"nameAndValue")

    }

    private fun edit() {modelObject.edit();}

    private fun removeFromListOfProducts() {
        modelObject.deleteFromListOfProducts();
    }

    private fun addToListOfProducts() {
        modelObject.addToListOfProducts();
    }

}