package paymentMethod

import java.util.*
open class PaymentMethod () {


    fun creditCard(ownerName:String,number:String,securityNumber: String, dueDate: String):CreditCard{

        return CreditCard(ownerName,number,securityNumber,dueDate)
    }


    fun debitCard(ownerName:String,number:String,securityNumber: String, dueDate: String):Debit{

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