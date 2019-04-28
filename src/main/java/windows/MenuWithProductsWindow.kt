package windows

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
    override fun addActions(p0: Panel?) : Unit {}

    override fun createFormPanel(panel: Panel) {

        title = "Products In Menu"
        Label(panel)
                .setText("Products")
                .setFontSize(20)
                .alignCenter();
        panel.setLayout(VerticalLayout()).setWidth(2000)

        var listOfProducts = List<ProductModel>(panel)
        listOfProducts.bindItemsToProperty("productsOfMenu")
                      .adaptWith(ProductModel::class.java,"nameAndPrice")


        var lastPanel = Panel(panel)
        lastPanel.setLayout(HorizontalLayout())
        Label(lastPanel).setText("TOTAL $").alignLeft()
        var totalLabel = Label(lastPanel).setText("monto").alignLeft()
        totalLabel.bindValueToProperty<Double,ControlBuilder>("currentTotal")


        Button(panel)
                .setCaption("Accept")
                .onClick {
                    this.close();
                   }
    }
}