package windows

import org.uqbar.commons.model.annotations.Observable

@Observable
abstract class ObservableBoolean(var getValue : Boolean ){
    //var getValue : String = bValue.toString()
}
@Observable
class Enabled : ObservableBoolean(true)


@Observable
class Disabled : ObservableBoolean(false)
