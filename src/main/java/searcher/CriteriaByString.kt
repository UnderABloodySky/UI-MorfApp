package searcher

class CriteriaByString(private var name : String) : Criteria() {
    /*
    Version Original:
    override fun search(toSearch: MutableMap<Int, *>) : MutableList<*>{
        var list : MutableList<*> = mutableListOf()

        // toSearch.forEach {(key, value) ->
        //     if (value.javaClass.kotlin.declaredMemberProperties.first { it.name == "name" }.get(value) == name ||
        //         value.javaClass.kotlin.declaredMemberProperties.first { it.name == "description" }.get(value) == name)
        //         { list.add(value) }};
        return list;
    }
    */

    override fun search(toSearch: MutableMap<Int, Searchable>) : MutableCollection<Searchable?>{
       var listResult : MutableCollection<Searchable?> = mutableListOf<Searchable?>()

       toSearch.forEach {(code, searcheable) ->
            if (searcheable.name.contains(name) || searcheable.description.contains(name)){
                listResult.add(searcheable)
            }
       }
       return listResult
    }
}