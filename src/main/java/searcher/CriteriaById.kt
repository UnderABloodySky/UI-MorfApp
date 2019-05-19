package searcher

import exception.NoIDException
import java.lang.NullPointerException

class CriteriaById(private var id : Int?) : Criteria(){

    override fun search(toSeach: MutableMap<Int, Searchable>) : MutableCollection<Searchable?> {

        var res: MutableCollection<Searchable?> =  mutableListOf()
        var entity = toSeach.get(id)
        if (entity != null){
            res.add(entity
            )
        }
        return res
    }
}