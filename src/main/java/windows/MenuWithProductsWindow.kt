package windows

import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder

class MenuWithProductsWindow(owner: WindowOwner, model: MenuModel?) : SimpleWindow<MenuModel>(owner, model) {
    override fun addActions(p0: Panel?) : Unit {}

    override fun createFormPanel(panel: Panel) {

        title = "Products In Menu"
        Label(panel)
                .setText("Products")
                .setFontSize(20)
                .alignCenter();
        panel.setLayout(VerticalLayout())

        var productTable = Table<ProductModel>(panel, ProductModel::class.java);
        productTable.bindItemsToProperty("products")
        productTable.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct")
        Column<ProductModel>(productTable)
                .setTitle("Name")
                .setFixedSize(250)
                .bindContentsToProperty("price");

        Button(panel)
                .setCaption("Accept")
                .onClick {
                    this.close();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()}
    }
}