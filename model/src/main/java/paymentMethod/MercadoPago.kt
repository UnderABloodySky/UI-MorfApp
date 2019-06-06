package paymentMethod

class MercadoPago (user:String,password:String):PaymentMethod(){
    var typePM = "MercadoPago"
    var user=user
    var password= password


    fun isMercadoPagoType(type:String):Boolean{
        return  typePM ==type

    }
}