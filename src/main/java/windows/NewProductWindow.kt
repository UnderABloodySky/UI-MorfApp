package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.ControlBuilder
import productAndMenu.Category


class NewProductWindow(model: ProductModel) : MainWindow<ProductModel>(model) {

    override fun createContents(panel: Panel) {
        title = "Restaurant :: New Product";

        Label(panel)
                .setText("New Product")
                .setFontSize(30)
                .alignCenter();

        this.setTextboxPanel(panel);
        this.setFourColumnPanel(panel);

        Button(panel)
            .setCaption("Cancel");
        Button(panel)
            .setCaption("Accept")
            .onClick { this.save() };

    }

    private fun setTextboxPanel(panel : Panel){

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2)).setWidth(100);
        Label(columnPanel).setText("Code");
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<Int, ControlBuilder>("code");

        Label(columnPanel).setText("Name");
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("name");

        Label(columnPanel).setText("Description");
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("description");

    }
    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4));
        Label(fourColumnPanel).setText("Price");
        TextBox(fourColumnPanel)
                .setWidth(80)
                .bindValueToProperty<String, ControlBuilder>("price");

        Label(fourColumnPanel)
                .setText("Category")
        val categorySelector = Selector<String>(fourColumnPanel)
        categorySelector.bindValueToProperty<Category, ControlBuilder>("category");

    }
    private fun save() { modelObject.save(); }

}