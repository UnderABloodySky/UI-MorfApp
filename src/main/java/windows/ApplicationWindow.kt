package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.ControlBuilder

class ApplicationWindow(model: ApplicationModel) : MainWindow<ApplicationModel>(model) {

    override fun createContents(panel: Panel) {
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

        var buttonMainPanel = Panel(productPanel);
        buttonMainPanel
                .setLayout(ColumnLayout(2));

        var buttonLeftPanel = Panel(buttonMainPanel);
        Button(buttonLeftPanel)
                .setCaption("Modify Product")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
        Button(buttonLeftPanel)
                .setCaption("Add Product")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }

        var buttonRightPanel = Panel(buttonMainPanel);
        Button(buttonRightPanel)
                .setCaption("View Menu")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
        Button(buttonRightPanel)
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

    }
}