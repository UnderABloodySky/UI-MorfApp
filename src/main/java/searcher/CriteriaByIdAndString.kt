package searcher

class CriteriaByIdAndString(val parameter : Any?) : Criteria() {

    override fun search(toSearch: MutableMap<Int, Searchable>): MutableCollection<Searchable?> {
        var parcialList : MutableCollection<Searchable?> = mutableListOf()
        var listResult : MutableCollection<Searchable?> = mutableListOf()
        when(parameter){
            is Int -> parcialList.addAll(CriteriaByString(parameter.toString()).search(toSearch)
                                                + CriteriaById(parameter.toInt()).search(toSearch))
            is String -> parcialList = CriteriaByString(parameter).search(toSearch)
        }
        parcialList.forEach { if(!listResult.contains(it)){listResult.add(it) } }

        return listResult
    }
}

