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


class ProductInMenusWindow( owner: WindowOwner, model: ApplicationModel) : SimpleWindow<ApplicationModel>(owner, model) {

    override fun addActions(p0: Panel?) : Unit {}

    override fun createFormPanel(panel: Panel) {

        title = "Menus Of Product"
        Label(panel)
                .setText("Menus That Contain the product")
                .setFontSize(20)
                .alignCenter();
        panel.setLayout(VerticalLayout())

        val table = Table<MenuModel>(panel, MenuModel::class.java)
        table.bindValueToProperty<ApplicationModel, ControlBuilder>("selectedProduct")
        table.bindItemsToProperty("menusOfSelectedProduct")

        Column<MenuModel>(table)
                .setTitle("Menus")
                .setFixedSize(250)
                .bindContentsToProperty("name");
        Button(panel)
                .setCaption("Accept")
                .onClick {
                    this.close();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()};
    }
}