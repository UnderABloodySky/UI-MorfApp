package windows

import org.uqbar.arena.bindings.ValueTransformer

class NumericTransformer : ValueTransformer<Any, String> {

    override fun getModelType() = Any::class.java
    override fun getViewType() = String::class.java
    override fun modelToView(valueFromModel: Any): String = valueFromModel.toString()
    override fun viewToModel(valueFromView: String): Any {
        if( isParserToInt(valueFromView))
            return valueFromView.toInt()
        else{
            return valueFromView
        }
    }

    private fun isParserToInt(something : String) : Boolean{
        var res : Boolean
        try{
            Integer.parseInt(something)
            res = true
        }
        catch(e : Exception){
            res = false
        }
        return res
    }
}
