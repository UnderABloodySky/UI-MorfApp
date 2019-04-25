package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.ControlBuilder

class ApplicationWindow(model: ApplicationModel) : MainWindow<ApplicationModel>(model) {

    override fun createContents(panel: Panel) {
        title = "Morfapp :: Restaurant";

        val columnPanel = Panel(panel.setLayout(ColumnLayout(2)));
        columnPanel.container;
        Label(columnPanel)
                .setText("Product Administration")
                .setFontSize(25)
                .alignCenter();

        Label(columnPanel)
                .setText("Menu Administration")
                .setFontSize(25)
                .alignCenter();

        TextBox(columnPanel)
                .bindValueToProperty<Any, ControlBuilder>("productFilter");

        TextBox(columnPanel)
                .bindValueToProperty<Any, ControlBuilder>("menuFilter");

        var table = Table<ProductModel>(panel, ProductModel::class.java);
        table.bindItemsToProperty("products")
        table.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct")
        Column<ProductModel>(table)
                .setTitle("Name")
                .setFixedSize(250)
                .bindContentsToProperty("name");

        Column<ProductModel>(table)
                .setTitle("Price")
                .setFixedSize(250)
                .bindContentsToProperty("price");


        Button(panel)
                .setCaption("Add Product")
                .onClick {
                    val newProductWindow = NewProductWindow(this, ProductModel());
                    newProductWindow.open();
                }
    }
}