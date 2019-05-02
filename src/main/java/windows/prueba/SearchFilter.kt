package windows.prueba

import org.uqbar.arena.filters.TextFilter
import org.uqbar.arena.widgets.TextInputEvent
import org.uqbar.commons.model.exceptions.UserException

class SearchFilter : TextFilter {

    override fun accept(event: TextInputEvent): Boolean {
        // val pattern_onlyAlphanumeric = "[^A-Za-z0-9 ]"
        var result = false
        //return event.potentialTextResult.matches(Regex("[^A-Za-z0-9 ]"))
        try {
            Integer.parseInt(event.potentialTextResult)
            result = true
        } catch (e: NumberFormatException) {
            throw UserException("No Number", e)
        }
        return result
    }
}


