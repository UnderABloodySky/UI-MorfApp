package windows

import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.List
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner


class ProductInMenusWindow( owner: WindowOwner, model: ProductInMenusModel) : SimpleWindow<ProductInMenusModel>(owner, model) {

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {

        title = "Productos Del Menu"
        Label(panel)
                .setText("Producto contenido en:")
                .setFontSize(20)
                .alignCenter()

        panel.setLayout(VerticalLayout())
                .setWidth(2000)

        var listOfMenusWithTheProduct = List<MenuModel>(panel)
        listOfMenusWithTheProduct.bindItemsToProperty("menusOfSelectedProduct")
                                 .adaptWith(MenuModel::class.java,"name")

        Button(panel)
                .setCaption("Aceptar")
                .onClick {
                    this.close()
                    var applicationModel = ApplicationModel(modelObject.applicationModel.restaurantModel)
                    ApplicationWindow(this, applicationModel).open()}
    }
}