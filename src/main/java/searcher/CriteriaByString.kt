package searcher

class CriteriaByString(private var name : String) : Criteria() {

    override fun search(toSearch: MutableMap<Int, Searchable>) : MutableCollection<Searchable?>{
       var listResult : MutableCollection<Searchable?> = mutableListOf<Searchable?>()

       toSearch.forEach {(code, searcheable) ->
            if (searcheable.name.contains(name)){
                listResult.add(searcheable)
            }
       }
       return listResult
    }
}