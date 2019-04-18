package searcher

open class Searcher<T>() {

    fun search() : MutableCollection<T>?{
            return null
        }

        fun searchBy(criteria : Criteria<T>, toSearch : MutableMap<Int, T>) : MutableList<T?>{
            return criteria.search(toSearch);
    }
}