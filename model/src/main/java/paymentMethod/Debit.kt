package paymentMethod

import java.util.*

class Debit (ownerName:String,number:String,securityNumber:String,dueDate: String):PaymentMethod(){

    var typePM = "DebitCard"
    var ownerName= ownerName
    var number = number
    var securityNumber = securityNumber
    var dueDate = dueDate


    fun isDebitCardType(type:String):Boolean{
        return  typePM ==type

    }
}