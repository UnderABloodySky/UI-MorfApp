package searcher

class CriteriaByIdAndString<T>(private var algo : Any,
                               private var idCriteria : CriteriaById<T>,
                               private var stringCriteria : CriteriaByString<T>) : Criteria<T>() where T : Any {


    override fun search(toSearch: MutableMap<Int, T>): MutableList<T?> {

        var idList      = this.idCriteria.search(toSearch);
        var stringList  = this.stringCriteria.search(toSearch);

        if (!stringList.contains(idList.first())){
            stringList.add(idList.first());
        }
        return stringList;
    }

}