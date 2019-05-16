package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
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

class MenuWithProductsWindow(owner: WindowOwner, model: MenuModel?) : SimpleWindow<MenuModel>(owner, model) {
    override fun addActions(p0: Panel?){}

    override fun createFormPanel(panel: Panel) {

        title = "Productos en Menu"
        Label(panel)
                .setText("Productos")
                .setFontSize(20)
                .alignCenter();
        panel.setLayout(VerticalLayout()).setWidth(2000)

        var listOfProducts = List<ProductModel>(panel)
        listOfProducts.bindItemsToProperty("productsOfMenu")
                      .adaptWith(ProductModel::class.java,"nameAndPrice")

        var lastPanel = Panel(panel).setLayout(ColumnLayout(2)).setWidth(2000)


        Label(lastPanel).setText("Sub total        $ ")
        var subTotalLabel = Label(lastPanel)
        subTotalLabel.bindValueToProperty<Double,ControlBuilder>("price")

        Label(lastPanel).setText("Total con descuento$ ")
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