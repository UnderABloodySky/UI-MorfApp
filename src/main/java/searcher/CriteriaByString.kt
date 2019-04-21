package searcher

import kotlin.reflect.full.declaredMemberProperties

class CriteriaByString<T>(private var aString : String) : Criteria<T>() where T : Any {

    override fun search(toSearch: MutableMap<Int, T>) : MutableList<T?>{
        var list : MutableList<T?> = mutableListOf()

        toSearch.forEach {(key, value) ->
            if (value.javaClass.kotlin.declaredMemberProperties.first { it.name == "name" }.get(value) == aString ||
                value.javaClass.kotlin.declaredMemberProperties.first { it.name == "description" }.get(value) == aString)
                { list.add(value) }};
        
        return list;
    }
}