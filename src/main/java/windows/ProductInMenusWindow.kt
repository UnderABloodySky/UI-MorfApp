package windows

import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.List
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
                .setText("Product contained in:")
                .setFontSize(20)
                .alignCenter();

        panel.setLayout(VerticalLayout())
                .setWidth(2000)

        var listOfMenusWithTheProduct = List<MenuModel>(panel)
        listOfMenusWithTheProduct.bindItemsToProperty("menusOfSelectedProduct")
                                 .adaptWith(MenuModel::class.java,"name")



        Button(panel)
                .setCaption("Accept")
                .onClick {
                    this.close();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()};
    }
}