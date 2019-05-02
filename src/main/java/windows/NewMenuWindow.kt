package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder

class NewMenuWindow(owner: WindowOwner, model: MenuModel) : SimpleWindow<MenuModel>(owner, model) {

    override fun addActions(p0: Panel?): Unit {}

    override fun createFormPanel(panel: Panel) {
        title = "Restaurant :: Add Menu";

        Label(panel)
                .setText("Add Menu")
                .setFontSize(30)
                .alignCenter();

        this.setTextBoxPanel(panel);
        this.setFourColumnPanel(panel);

        Button(panel)
                .setCaption("Accept")
                .onClick {
                    this.save();
                    this.close();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()
                };
        Button(panel)
                .setCaption("Cancel")
                .onClick {
                    this.close();
                    var applicationModel = ApplicationModel(modelObject.restaurantModel);
                    ApplicationWindow(this, applicationModel).open()
                };

    }

    private fun setTextBoxPanel(panel: Panel) {

        var columnPanel = Panel(panel).setLayout(ColumnLayout(2));
        Label(columnPanel).setText("Name");
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("name");

        Label(columnPanel).setText("Description");
        TextBox(columnPanel)
                .setWidth(150)
                .bindValueToProperty<String, ControlBuilder>("description");

        Label(columnPanel)
                .setText("Enabled");
        val enabledSelector = RadioSelector<ObservableBoolean>(columnPanel)
        enabledSelector.bindValueToProperty<Boolean,ControlBuilder>("enabled")

        enabledSelector.bindItemsToProperty("observableBooleans")
                .adaptWith(ObservableBoolean::class.java, "getValue")
    }

    private fun setFourColumnPanel(panel: Panel) {

        var fourColumnPanel = Panel(panel).setLayout(ColumnLayout(4));

        Label(fourColumnPanel)
                .setText("Disc. Type");
        val discountSelector = Selector<DiscountModel>(fourColumnPanel);
        discountSelector.bindItemsToProperty("discounts")
                .adaptWith(DiscountModel::class.java,"nameAndValue")
        discountSelector.bindValueToProperty<DiscountModel, ControlBuilder>("discount")

        Label(fourColumnPanel)
                .setText("Discount")
        TextBox(fourColumnPanel).bindValueToProperty<DiscountModel, ControlBuilder>("discount")


    }

    private fun save() {modelObject.save();}




}