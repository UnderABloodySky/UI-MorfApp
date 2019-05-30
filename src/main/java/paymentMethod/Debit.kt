package paymentMethod

import java.util.*

class Debit (ownerName:String,number:Int,securityNumber:Int,dueDate: Date):PaymentMethod(){

    var ownerName= ownerName
    var number = number
    var securityNumber = securityNumber
    var dueDate = dueDate

}