package windows

import geoclaseui.ProductModel
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.MainWindow


class NewProductWindow(model: ProductModel) : MainWindow<ProductModel>(model) {

    override fun createContents(panel: Panel) {
        title = "Restaurant :: New Product";

        Label(panel)
                .setText("New Product")
                .setFontSize(30)
                .alignLeft();

        this.setTextboxPanel(panel);
        this.setFourColumnPanel(panel);

        Button(panel)
            .setCaption("Cancel");
        Button(panel)
            .setCaption("Accept")
            .onClick { this.save() };


    }

    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4));
        this.setColumnLayout(fourColumnPanel, "Price");

        Label(fourColumnPanel)
                .setText("Category")
        val selector = Selector<String>(fourColumnPanel);

    }
    private fun setTextboxPanel(panel : Panel){

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2)).setWidth(120);
        this.setColumnLayout(columnPanel, "Code")
        this.setColumnLayout(columnPanel, "Name")
        this.setColumnLayout(columnPanel, "Description")

    }
    private fun setColumnLayout(panel: Panel, name : String){
        Label(panel).setText(name); TextBox(panel)
    }

    private fun save() { modelObject.save(); }

}