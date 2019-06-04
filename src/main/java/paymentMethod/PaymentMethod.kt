package paymentMethod

import java.util.*
open class PaymentMethod () {

    //la idea es que este metodo vaya viendo cual tiene que crear  , 
    fun suitableClassFinder(type:String):PaymentMethod {
        if (type == "Cash") {

        }

    }

    fun creditCard(ownerName:String,number:Int,securityNumber: Int, dueDate: Date):CreditCard{

        return CreditCard(ownerName,number,securityNumber,dueDate)
    }


    fun debitCard(ownerName:String,number:Int,securityNumber: Int, dueDate: Date):Debit{

        return Debit(ownerName,number,securityNumber,dueDate)
    }

    fun mercadoPago(user:String,password:String):MercadoPago{
        return MercadoPago(user,password)
    }

    fun cash():Cash{
        return Cash()
    }

    fun paypal(user:String,password:String):PayPal{
        return PayPal(user,password)
    }

}