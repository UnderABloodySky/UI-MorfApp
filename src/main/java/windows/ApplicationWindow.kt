package windows

import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.MainWindow

class ApplicationWindow(model: ApplicationModel) : MainWindow<ApplicationModel>(model) {

    override fun createContents(panel: Panel) {
        title = "Morfapp :: Restaurant";

        panel.setLayout(ColumnLayout(2));

        Label(panel)
                .setText("Product Administration")
                .setFontSize(25)
                .alignCenter();

        Label(panel)
                .setText("Menu Administration")
                .setFontSize(25)
                .alignCenter();

        Button(panel).onClick { NewProductWindow(this, ProductModel()).open(); }

        }


}