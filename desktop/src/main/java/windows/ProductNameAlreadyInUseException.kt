package exception

import org.uqbar.commons.model.exceptions.UserException

class ProductNameAlreadyInUseException(message: String) : UserException(message)
