package searcher

import kotlin.reflect.full.declaredMemberProperties

class CriteriaByString<T>(private var name : String) : Criteria<T>() where T : Any {

    override fun search(toSearch: MutableMap<Int, T>) : MutableList<T?>{
        var list : MutableList<T?> = mutableListOf()

        toSearch.forEach {(key, value) ->
            if (value.javaClass.kotlin.declaredMemberProperties.first { it.name == "name" }.get(value) == name ||
                value.javaClass.kotlin.declaredMemberProperties.first { it.name == "description" }.get(value) == name)
                { list.add(value) }};
        
        return list;
    }
}