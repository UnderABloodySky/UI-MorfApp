package searcher

class Searcher {

        fun searchBy(criteria : Criteria, toSearch : MutableMap<Int, Searchable>) : MutableCollection<Searchable?>{
            return criteria.search(toSearch)
    }
}