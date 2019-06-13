package windows

import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.List
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Observable
import productAndMenu.Category


class ProductInMenusWindow( owner: WindowOwner, model: ProductInMenusModel) : SimpleWindow<ProductInMenusModel>(owner, model) {

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {

        title = "Menus que contienen el producto"

        var product = modelObject.productName

        Label(panel).setText("Detalle de "+ product).setFontSize(20)
        this.setTextBoxPanel(panel)

        Label(panel)
                .setText("Está contenido en:")
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



    private fun setTextBoxPanel(panel : Panel){
      //  val elementCode: Observable<Any> = NotNullObservable("observableNull")

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2)).setWidth(2000)


        Label(columnPanel).setText("Codigo")
        Label (columnPanel).bindValueToProperty<Int,ControlBuilder>("code")


        Label(columnPanel).setText("Nombre")
        Label (columnPanel).bindValueToProperty<String,ControlBuilder>("productName")


        Label(columnPanel).setText("Descripción")
        Label (columnPanel).bindValueToProperty<String,ControlBuilder>("description")

        Label(columnPanel).setText("Precio")
        Label(columnPanel).bindValueToProperty<Double,ControlBuilder>("price")

        Label(columnPanel).setText("Categoria")
        Label(columnPanel).bindValueToProperty<String,ControlBuilder>("category")



    }



}