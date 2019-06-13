package exception

class EmptyOrderException(message: String) : Exception(message)

class MenuNameAlreadyInUseException(message: String) : Exception(message)

class NoRestaurantFoundException (message: String) : Exception(message)

class NoUserAuthenticateException(message: String) : Exception(message)

class NoValidateOrderException(message : String) : Exception(message)

class UserAlreadyRegisteredException(menssage: String): Exception(menssage)

class UserNoFoundException(message:String ): Exception(message)

class IncorrectDiscountValueException (message: String) : Exception(message)
    fun main(args: Array<String>) {
        throw IncorrectDiscountValueException("Error!")
}