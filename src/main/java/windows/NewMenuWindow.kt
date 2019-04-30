package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import productAndMenu.Category

class NewMenuWindow(owner: WindowOwner, model: MenuModel) : SimpleWindow<MenuModel>(owner, model) {

    override fun addActions(p0: Panel?): Unit {}

    override fun createFormPanel(panel: Panel) {
        title = "Restaurant :: Add Menu";

        Label(panel)
                .setText("Add Menu")
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
        val allProductsSelector = Selector<ProductModel>(listOfProductsColumnPanel);

        allProductsSelector.bindItemsToProperty("availableProducts")
                .adaptWith(ProductModel::class.java,"nameAndPrice")
        allProductsSelector.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct");
        allProductsSelector.bindItemsToProperty("availableProducts");

        var addRemoveButtonPanel = Panel(listOfProductsColumnPanel).setLayout(VerticalLayout());
        Button(addRemoveButtonPanel)
                .setCaption(">>")
        Button(addRemoveButtonPanel)
                .setCaption("<<")

        val filteredProductsSelector = Selector<ProductModel>(listOfProductsColumnPanel);
        filteredProductsSelector.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct");
        filteredProductsSelector.bindItemsToProperty("productsOfMenu");

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

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2));
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

        var twoColumnPanel = Panel(panel).setLayout(ColumnLayout(2));

        Label(twoColumnPanel)
                .setText("Disc. Type");
        val categorySelector = Selector<Category>(twoColumnPanel);
        categorySelector.bindValueToProperty<Category, ControlBuilder>("discount");
        categorySelector.bindItemsToProperty("discounts");

    }

    private fun edit() {modelObject.edit();}

}