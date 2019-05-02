package windows.prueba

import org.uqbar.arena.widgets.Control
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.lacar.ui.model.bindings.Binding

class OnlyNumbersBox(container : Panel) :  TextBox(container){
    override fun <M, C: ControlBuilder> bindValueToProperty(propertyName:
                                                            String): Binding<M, Control, C> {
        val binding = super.bindValueToProperty<M, C>(propertyName)
        binding.setTransformer(OnlyNumberTransformer())
        this.withFilter(OnlyNumberFilter())
        return binding
    }

}