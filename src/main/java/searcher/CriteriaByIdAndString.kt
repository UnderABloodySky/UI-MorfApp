package searcher

/*
Esto es lo que estaba:
class CriteriaByIdAndString(private var idCriteria : CriteriaById,
                               private var stringCriteria : CriteriaByString) : Criteria() {
    override fun search(toSearch: MutableMap<Int, Searchable>): MutableCollection<Searchable?> {
        var idList      = this.idCriteria.search(toSearch);
        var stringList  = this.stringCriteria.search(toSearch);

        if (!stringList.contains(idList.first())){
    //        stringList.add(idList.first());
        }
        return stringList;
    }
}
*/

class CriteriaByIdAndString(val parameter : Any) : Criteria() {

    override fun search(toSearch: MutableMap<Int, Searchable>): MutableCollection<Searchable?> {
        var parcialList : MutableCollection<Searchable?> = mutableListOf()
        var listResult : MutableCollection<Searchable?> = mutableListOf()
        when(parameter){
            is Int -> parcialList.addAll(CriteriaById(parameter).search(toSearch) + CriteriaByString(parameter.toString()).search(toSearch))
            is String -> parcialList = CriteriaByString(parameter).search(toSearch)
        }

        //Preguntar: Esta forma es muy fea para eliminar repetidos
        parcialList.forEach { if(!listResult.contains(it)){listResult.add(it) } }

        return listResult
    }
}

