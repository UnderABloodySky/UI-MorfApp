package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.MainWindow
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder
import productAndMenu.Category


class EditProductWindow(owner: WindowOwner, model: ProductModel?) : SimpleWindow<ProductModel>(owner, model) {
    override fun addActions(p0: Panel?) : Unit {}

    override fun createFormPanel(panel: Panel) {
        title = "Restaurant :: Edit Product";

        Label(panel)
                .setText("Edit Product")
                .setFontSize(30)
                .alignCenter();

        this.setTextBoxPanel(panel);
        this.setFourColumnPanel(panel);

        Button(panel)
                .setCaption("Accept")
                .onClick {  this.edit();
                            this.close();
                            var applicationModel = ApplicationModel(modelObject.restaurantModel);
                            ApplicationWindow(this, applicationModel).open()};
        Button(panel)
                .setCaption("Cancel")
                .onClick {  this.close();
                            var applicationModel = ApplicationModel(modelObject.restaurantModel);
                            ApplicationWindow(this, applicationModel).open()};

    }

    private fun setTextBoxPanel(panel : Panel){

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2)).setWidth(100);

        Label(columnPanel).setText("Code");
        val codeTextBox = TextBox(columnPanel);
        codeTextBox.setWidth(150)
        codeTextBox.bindValueToProperty<Int, ControlBuilder>("code");
        codeTextBox.withFilter { event -> event.potentialTextResult.matches(Regex("[0-9]*")) }

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
                .setText("Category");
        val categorySelector = Selector<Category>(fourColumnPanel);
        categorySelector.bindValueToProperty<Category, ControlBuilder>("category");
        categorySelector.bindItemsToProperty("categories");

    }
    private fun edit() { modelObject.edit(); }
}