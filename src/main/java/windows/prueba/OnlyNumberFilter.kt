package windows.prueba

import org.uqbar.arena.filters.TextFilter
import org.uqbar.arena.widgets.TextInputEvent
import org.uqbar.commons.model.exceptions.UserException

class OnlyNumberFilter : TextFilter {

    override fun accept(event: TextInputEvent): Boolean {
        var result = false
        try {
            Integer.parseInt(event.potentialTextResult)
            result = true
        } catch (e: NumberFormatException) {
            throw UserException("No Number", e)
        }
        return result
    }
}


