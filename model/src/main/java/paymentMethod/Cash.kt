package paymentMethod




class Cash ():PaymentMethod(){
    val typePM = "Cash"

    fun isCashType(type:String):Boolean{
        return  typePM ==type

    }
}