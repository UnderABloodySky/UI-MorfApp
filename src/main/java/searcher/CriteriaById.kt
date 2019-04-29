package searcher

class CriteriaById(private var id : Int) : Criteria(){

    override fun search(toSeach: MutableMap<Int, Searchable>) : MutableCollection<Searchable?> {
        var entity = toSeach.get(id)
        return mutableListOf(entity)
    }
}