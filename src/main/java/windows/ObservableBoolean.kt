package windows

import org.uqbar.commons.model.annotations.Observable

@Observable
abstract class ObservableBoolean(var getValue : Boolean, var optionName : String )

@Observable
class Enabled : ObservableBoolean(true, "Enabled")

@Observable
class Disabled : ObservableBoolean(false, "Disabled")
