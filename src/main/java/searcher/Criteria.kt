package searcher

abstract class Criteria() {
    abstract fun search(toSeach: MutableMap<Int, Searchable>) : MutableCollection<Searchable?>
}
