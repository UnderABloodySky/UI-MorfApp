package searcher

//Estoquizas necesite un adapter

abstract class Criteria<T>() {
    abstract fun search(toSeach: MutableMap<Int, T>) : MutableList<T?>;
}