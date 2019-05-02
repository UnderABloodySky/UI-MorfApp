package windows

import org.uqbar.commons.model.annotations.Observable

@Observable
abstract class ObservableBoolean(var getValue : Boolean ){
    //var getValue : String = bValue.toString()
}
@Observable
class OTrue : ObservableBoolean(true)


@Observable
class OFalse : ObservableBoolean(false)
