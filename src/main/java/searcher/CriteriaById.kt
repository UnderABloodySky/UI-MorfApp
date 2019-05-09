package searcher

import java.lang.NullPointerException

class CriteriaById(private var id : Int?) : Criteria(){

    override fun search(toSeach: MutableMap<Int, Searchable>) : MutableCollection<Searchable?> {
        var res: MutableCollection<Searchable?> =  mutableListOf()
        try{
            var entity = toSeach.get(id)
            res.add(entity)
        }
        catch (e : NullPointerException){

        }

        return res
    }
}