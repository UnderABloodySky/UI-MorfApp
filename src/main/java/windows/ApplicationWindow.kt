package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder


class ApplicationWindow(owner: WindowOwner, model: ApplicationModel) : SimpleWindow<ApplicationModel>(owner, model) {

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {
        title = "Morfapp :: Restaurant";

        panel.setLayout(HorizontalLayout());

        val productPanel = Panel(panel);

        Label(productPanel)
                .setText("Product Administration")
                .setFontSize(25)
                .alignCenter();

        TextBox(productPanel)
                .bindValueToProperty<Any, ControlBuilder>("productFilter");

        var productTable = Table<ProductModel>(productPanel, ProductModel::class.java);
        productTable.bindItemsToProperty("products")
        productTable.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct")
        Column<ProductModel>(productTable)
                .setTitle("Name")
                .setFixedSize(250)
                .bindContentsToProperty("name");

        Column<ProductModel>(productTable)
                .setTitle("Price")
                .setFixedSize(250)
                .bindContentsToProperty("price");

        var buttonProductPanel = Panel(productPanel);
        buttonProductPanel
                .setLayout(ColumnLayout(2));

        var buttonProductLeftPanel = Panel(buttonProductPanel);
        Button(buttonProductLeftPanel)
                .setCaption("Modify Product")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
        Button(buttonProductLeftPanel)
                .setCaption("Add Product")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }

        var buttonProductRightPanel = Panel(buttonProductPanel);
        Button(buttonProductRightPanel)
                .setCaption("View Menu")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
        Button(buttonProductRightPanel)
                .setCaption("Delete Product")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }

        val menuPanel = Panel(panel);

        Label(menuPanel)
                .setText("Menu Administration")
                .setFontSize(25)
                .alignCenter();

        TextBox(menuPanel)
                .bindValueToProperty<Any, ControlBuilder>("menuFilter");

        var menuTable = Table<MenuModel>(menuPanel, MenuModel::class.java);
        menuTable.bindItemsToProperty("menus")
        menuTable.bindValueToProperty<MenuModel, ControlBuilder>("selectedMenu")
        Column<MenuModel>(menuTable)
                .setTitle("Name")
                .setFixedSize(250)
                .bindContentsToProperty("name");

        Column<MenuModel>(menuTable)
                .setTitle("Price")
                .setFixedSize(250)
                .bindContentsToProperty("price");

        Column<MenuModel>(menuTable)
                .setTitle("enabled")
                .setFixedSize(250)
                .bindContentsToProperty("enabled");

        var buttonMenuPanel = Panel(menuPanel);
        buttonMenuPanel.setLayout(HorizontalLayout());

        Button(buttonMenuPanel)
                .setCaption("View Menu")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
        Button(buttonMenuPanel)
                .setCaption("Add Menu")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }

        Button(buttonMenuPanel)
                .setCaption("Edit Menu")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
        Button(buttonMenuPanel)
                .setCaption("Delete Menu")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
    }
}