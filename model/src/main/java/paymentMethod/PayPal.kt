package paymentMethod

class PayPal (user:String,password:String):PaymentMethod() {
    var typePM = "PayPal"
    var user=user
    var password= password



    fun isPayPalType(type:String):Boolean{
        return  typePM ==type

    }
}