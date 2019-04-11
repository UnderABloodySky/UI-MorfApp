package exception

class IncorrectDiscountValueException (message: String) : Exception(message)

fun main(args: Array<String>) {
    throw IncorrectDiscountValueException("Error!");
}