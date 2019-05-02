package windows.prueba

import org.uqbar.arena.bindings.ValueTransformer
import org.uqbar.commons.model.exceptions.UserException
import java.lang.NumberFormatException

class OnlyNumberTransformer : ValueTransformer<Int, String>{

        override fun getModelType() = Int::class.java

        override fun getViewType() = String::class.java

        override fun modelToView(valueFromModel: Int): String = valueFromModel.toString()

        override fun viewToModel(valueFromView : String): Int = Integer.parseInt(valueFromView)
}