package exception

import org.uqbar.commons.model.exceptions.UserException

class MenuNameAlreadyInUseException(message: String) : UserException(message)