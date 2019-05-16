package windows

import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Observable

class ApplicationWindow(owner: WindowOwner, model: ApplicationModel) : SimpleWindow<ApplicationModel>(owner, model){

    val elementProduct: Observable<Any> = NotNullObservable("selectedProduct")
    val elementMenu: Observable<Any> = NotNullObservable("selectedMenu")

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {
       title = "Morfapp :: ${modelObject.restaurantModel.name}"

       val logoutPanel = Panel(panel)
       var logOutButton = Button(logoutPanel)
                logOutButton.setCaption("Cerrar sesión")
                logOutButton.alignLeft()
                logOutButton.onClick {
                                    this.close()
                                    LoginWindow(this,UserModel()).open()
                        }

        val contentsPanel = Panel(panel)
        contentsPanel.setLayout(HorizontalLayout())

        val productPanel = Panel(contentsPanel)

        Label(productPanel)
                .setText("Administración De Productos")
                .setFontSize(25)
                .alignCenter()

        val searchProductPanel = Panel(productPanel)
        searchProductPanel.setLayout(HorizontalLayout())
        TextBox(searchProductPanel)
                .setWidth(200)
                .bindValueToProperty<Any, ControlBuilder>("productFilter")
                .setTransformer(NumericTransformer())

        Button(searchProductPanel)
                .setCaption("Buscar")
                .onClick { modelObject.updateProductList() }

        var productTable = Table<ProductModel>(productPanel, ProductModel::class.java)
        productTable.bindItemsToProperty("products")
        productTable.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct")

        Column<ProductModel>(productTable)
                .setTitle("Nombre")
                .setFixedSize(250)
                .bindContentsToProperty("name")

        Column<ProductModel>(productTable)
                .setTitle("Código")
                .setFixedSize(250)
                .bindContentsToProperty("code")

        Column<ProductModel>(productTable)
                .setTitle("Precio")
                .setFixedSize(250)
                .bindContentsToProperty("price")


        var buttonProductPanel = Panel(productPanel)
        buttonProductPanel
                .setLayout(ColumnLayout(2))

        var buttonProductLeftPanel = Panel(buttonProductPanel)
        Button(buttonProductLeftPanel)
                .setCaption("Nuevo Producto")
                .onClick {
                    this.close()
                    var tempProduct = ProductModel(modelObject.restaurantModel)
                    val newProductWindow = ProductWindow(this, tempProduct.save())
                    newProductWindow.open()
                }
        var editButton = Button(buttonProductLeftPanel)
                .setCaption("Editar Producto")
                .onClick {
                    this.close()
                    val newProductWindow = ProductWindow(this, modelObject.selectedProduct)
                    newProductWindow.open()
                }

        var buttonProductRightPanel = Panel(buttonProductPanel)
        var viewButton = Button(buttonProductRightPanel)
                .setCaption("Ver Producto")
                .onClick {
                    this.close()
                    val newProductInMenusWindow = ProductInMenusWindow(this, ProductInMenusModel(modelObject))
                    newProductInMenusWindow.open()
                }

        var deleteButton = Button(buttonProductRightPanel)
                .setCaption("Borrar Producto")
                .onClick {
                    this.close()
                    val deleteProductDialog = DeleteProductDialog(this, modelObject.selectedProduct)
                    deleteProductDialog.open()
                }
        editButton.bindEnabled<Any, ControlBuilder>(elementProduct)
        viewButton.bindEnabled<Any, ControlBuilder>(elementProduct)
        deleteButton.bindEnabled<Any, ControlBuilder>(elementProduct)

        val menuPanel = Panel(contentsPanel)

        Label(menuPanel)
                .setText("Administración De Los Menues")
                .setFontSize(25)
                .alignCenter()

        val searchMenuPanel = Panel(menuPanel)
        searchMenuPanel.setLayout(HorizontalLayout())
        TextBox(searchMenuPanel)
                .setWidth(200)
                .bindValueToProperty<Any, ControlBuilder>("menuFilter")
                .setTransformer(NumericTransformer())

        Button(searchMenuPanel)
                .setCaption("Buscar")
                .onClick { modelObject.updateMenuList() }


        var menuTable = Table<MenuModel>(menuPanel, MenuModel::class.java)
        menuTable.bindItemsToProperty("menus")
        menuTable.bindValueToProperty<MenuModel, ControlBuilder>("selectedMenu")
        Column<MenuModel>(menuTable)
                .setTitle("Nombre")
                .setFixedSize(250)
                .bindContentsToProperty("name")

        Column<MenuModel>(menuTable)
                .setTitle("Código")
                .setFixedSize(250)
                .bindContentsToProperty("code")

        Column<MenuModel>(menuTable)
                .setTitle("Precio")
                .setFixedSize(250)
                .bindContentsToProperty("totalWithDiscount")


        var enabledColumn = Column<MenuModel>(menuTable)
        enabledColumn.setTitle("Habilitado")
        enabledColumn.setFixedSize(250)
        enabledColumn.bindContentsToProperty("enabledNameStr")

        var buttonMenuPanel = Panel(menuPanel)
        buttonMenuPanel.setLayout(HorizontalLayout())

        Button(buttonMenuPanel)
                .setCaption("Nuevo Menú")
                .onClick {
                    this.close()
                    var tempMenu = MenuModel(modelObject.restaurantModel)
                    val newMenuWindow = MenuWindow(this, tempMenu.save())
                    newMenuWindow.open()
                }

        var viewMenuButton = Button(buttonMenuPanel)
                .setCaption("Ver Menú")
                .onClick {
                    this.close()
                    val newMenuWithProductsWindow = MenuWithProductsWindow(this, modelObject.selectedMenu)
                    newMenuWithProductsWindow.open()
                }

        var editMenuButton = Button(buttonMenuPanel)
                .setCaption("Editar Menú")
                .onClick {
                    this.close()
                    val newMenuWindow = MenuWindow(this, modelObject.selectedMenu)
                    newMenuWindow.open()
                }
        var deleteMenuButton = Button(buttonMenuPanel)
                .setCaption("Borrar Menú")
                .onClick {
                    this.close()
                    val newMenuWindow = DeleteMenuDialog(this, modelObject.selectedMenu)
                    newMenuWindow.open()
                }

        editMenuButton.bindEnabled<Any, ControlBuilder>(elementMenu)
        viewMenuButton.bindEnabled<Any, ControlBuilder>(elementMenu)
        deleteMenuButton.bindEnabled<Any, ControlBuilder>(elementMenu)

    }
}


