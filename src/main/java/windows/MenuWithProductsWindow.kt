package windows

import org.uqbar.arena.layout.HorizontalLayout
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
    override fun addActions(p0: Panel?){}

    override fun createFormPanel(panel: Panel) {

        title = "Productos en Menu"
        Label(panel)
                .setText("Productos del ${modelObject.name}")
                .setFontSize(20)
                .alignCenter()
        panel.setLayout(VerticalLayout()).setWidth(2000)

        var tableOfProducts = Table<ProductModel>(panel, ProductModel::class.java)
        tableOfProducts.bindItemsToProperty("productsOfMenu")


        Column<ProductModel>(tableOfProducts)
                .setTitle("Nombre")
                .setFixedSize(250)
                .bindContentsToProperty("name")

        Column<ProductModel>(tableOfProducts)
                .setTitle("Precio")
                .setFixedSize(250)
                .bindContentsToProperty("price")

        var firstPanel = Panel(panel).setLayout(HorizontalLayout())


        Label(firstPanel).setText("Sub total                                  ")
        Label(firstPanel).setText("")
        var subTotalLabel = Label(firstPanel)
        subTotalLabel.bindValueToProperty<Double,ControlBuilder>("price")


        var lastPanel = Panel(panel).setLayout(HorizontalLayout())

        Label(lastPanel).setText("Total con descuento         ")
        Label(lastPanel).setText("")
        var totalLabel = Label(lastPanel)
        totalLabel.bindValueToProperty<Double,ControlBuilder>("totalWithDiscount")
        totalLabel.alignLeft()

        Button(panel)
                .setCaption("Aceptar")
                .onClick {
                    this.close()
                    var applicationModel = ApplicationModel(modelObject.restaurantModel)
                    ApplicationWindow(this, applicationModel).open()
                   }
    }
}
