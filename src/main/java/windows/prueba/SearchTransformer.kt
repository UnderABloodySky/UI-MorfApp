package windows.prueba

import org.uqbar.arena.bindings.ValueTransformer
import org.uqbar.commons.model.exceptions.UserException
import java.lang.NumberFormatException

class SearchTransformer : ValueTransformer<Any, String>{

        override fun getModelType() = Any::class.java

        override fun getViewType() = String::class.java

        override fun modelToView(valueFromModel: Any): String = valueFromModel.toString()

        override fun viewToModel(valueFromView: String): Int = Integer.parseInt(valueFromView)
}