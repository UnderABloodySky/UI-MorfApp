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

    val elementProduct: Observable<Any> = NotNullObservable("selectedProduct");
    val elementMenu: Observable<Any> = NotNullObservable("selectedMenu");

    override fun addActions(p0: Panel?) {}

    override fun createFormPanel(panel: Panel) {
        title = "Morfapp :: ${modelObject.restaurantModel.name}";

       var logOutButton = Button(panel)
                logOutButton.setCaption("Log out")
                logOutButton.alignLeft()
                logOutButton.onClick {
                                    this.close()
                                    LoginWindow(this,UserModel()).open();
                        }

        panel.setLayout(HorizontalLayout());

        val productPanel = Panel(panel);

        Label(productPanel)
                .setText("Product Administration")
                .setFontSize(25)
                .alignCenter();

        val searchProductPanel = Panel(productPanel)
        searchProductPanel.setLayout(HorizontalLayout());
        TextBox(searchProductPanel)
                .bindValueToProperty<Any, ControlBuilder>("productFilter")
        Button(searchProductPanel)
                .setCaption("Search Name")
                .onClick { modelObject.updateProductList(); }

        NumericField(searchProductPanel)
                .bindValueToProperty<Int?, ControlBuilder>("productIdFilter")
        Button(searchProductPanel)
                .setCaption("Search by Id")
                .onClick { modelObject.updateProductListId(); }

        var productTable = Table<ProductModel>(productPanel, ProductModel::class.java);
        productTable.bindItemsToProperty("products")
        productTable.bindValueToProperty<ProductModel, ControlBuilder>("selectedProduct")
        Column<ProductModel>(productTable)
                .setTitle("Name")
                .setFixedSize(250)
                .bindContentsToProperty("name");

        Column<ProductModel>(productTable)
                .setTitle("Price")
                .setFixedSize(250)
                .bindContentsToProperty("price");

        var buttonProductPanel = Panel(productPanel);
        buttonProductPanel
                .setLayout(ColumnLayout(2));

        var buttonProductLeftPanel = Panel(buttonProductPanel);
        Button(buttonProductLeftPanel)
                .setCaption("New Product")
                .onClick {
                    this.close();
                    val newProductWindow = NewProductWindow(this, ProductModel(modelObject.restaurantModel));
                    newProductWindow.open();
                }
        var editButton = Button(buttonProductLeftPanel)
                .setCaption("Edit Product")
                .onClick {
                    this.close();
                    val newProductWindow = EditProductWindow(this, modelObject.selectedProduct);
                    newProductWindow.open()
                }

        var buttonProductRightPanel = Panel(buttonProductPanel);
        var viewButton = Button(buttonProductRightPanel)
                .setCaption("View Product")
                .onClick {
                    this.close();
                    val newProductInMenusWindow = ProductInMenusWindow(this, ProductInMenusModel(modelObject))
                    newProductInMenusWindow.open();
                }

        var deleteButton = Button(buttonProductRightPanel)
                .setCaption("Delete Product")
                .onClick {
                    this.close()
                    val deleteProductDialog = DeleteProductDialog(this, modelObject.selectedProduct);
                    deleteProductDialog.open();
                }
        editButton.bindEnabled<Any, ControlBuilder>(elementProduct);
        viewButton.bindEnabled<Any, ControlBuilder>(elementProduct);
        deleteButton.bindEnabled<Any, ControlBuilder>(elementProduct);

        val menuPanel = Panel(panel);

        Label(menuPanel)
                .setText("Menu Administration")
                .setFontSize(25)
                .alignCenter();

        val searchMenuPanel = Panel(menuPanel)
        searchMenuPanel.setLayout(HorizontalLayout());
        TextBox(searchMenuPanel)
                .bindValueToProperty<Any, ControlBuilder>("menuFilter")
        Button(searchMenuPanel)
                .setCaption("Search Name")
                .onClick { modelObject.updateMenuList(); }

        NumericField(searchMenuPanel)
                .bindValueToProperty<Int?, ControlBuilder>("menuIdFilter")
        Button(searchMenuPanel)
                .setCaption("Search ID")
                .onClick { modelObject.updateMenuIdList() }


        var menuTable = Table<MenuModel>(menuPanel, MenuModel::class.java);
        menuTable.bindItemsToProperty("menus")
        menuTable.bindValueToProperty<MenuModel, ControlBuilder>("selectedMenu")
        Column<MenuModel>(menuTable)
                .setTitle("Name")
                .setFixedSize(250)
                .bindContentsToProperty("name");

        Column<MenuModel>(menuTable)
                .setTitle("Price")
                .setFixedSize(250)
                .bindContentsToProperty("totalWithDiscount");

        var enabledColumn = Column<MenuModel>(menuTable)
        enabledColumn.setTitle("Enabled")
        enabledColumn.setFixedSize(250)
        enabledColumn.bindContentsToProperty("enabledName");


        var buttonMenuPanel = Panel(menuPanel);
        buttonMenuPanel.setLayout(HorizontalLayout());

        Button(buttonMenuPanel)
                .setCaption("Add Menu")
                .onClick {
                    this.close();
                    val newMenuWindow = NewMenuWindow(this, MenuModel(modelObject.restaurantModel));
                    newMenuWindow.open();
                }

        var viewMenuButton = Button(buttonMenuPanel)
                .setCaption("View Menu")
                .onClick {
                    this.close();
                    val newMenuWithProductsWindow = MenuWithProductsWindow(this, modelObject.selectedMenu);
                    newMenuWithProductsWindow.open();
                }

        var editMenuButton = Button(buttonMenuPanel)
                .setCaption("Edit Menu")
                .onClick {
                    this.close();
                    val newMenuWindow = EditMenuWindow(this, modelObject.selectedMenu);
                    newMenuWindow.open();
                }
        var deleteMenuButton = Button(buttonMenuPanel)
                .setCaption("Delete Menu")
                .onClick {
                    this.close();
                    val newMenuWindow = DeleteMenuDialog(this, modelObject.selectedMenu);
                    newMenuWindow.open();
                }

        editMenuButton.bindEnabled<Any, ControlBuilder>(elementMenu);
        viewMenuButton.bindEnabled<Any, ControlBuilder>(elementMenu);
        deleteMenuButton.bindEnabled<Any, ControlBuilder>(elementMenu);

    }
}


