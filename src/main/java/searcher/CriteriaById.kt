package searcher

class CriteriaById<T>(private var id : Int) {

    fun search(toSeach: MutableMap<Int, T>) : MutableList<T?> {
        var entity : T? = toSeach.get(id)
        return mutableListOf<T?>(entity);
    }
}