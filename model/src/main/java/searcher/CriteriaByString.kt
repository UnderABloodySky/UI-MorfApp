package searcher

class CriteriaByString(private var name : String) : Criteria() {

    override fun search(toSearch: MutableMap<Int, Searchable>) : MutableCollection<Searchable?>{
       var listResult : MutableCollection<Searchable?> = mutableListOf()

       toSearch.forEach {(code, searcheable) ->
            if (searcheable.name.toUpperCase().contains(name!!.toUpperCase())){
                listResult.add(searcheable)
            }
       }
       return listResult
    }
}