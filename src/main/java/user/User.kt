package user
import applicationModel.*



abstract class User( val code : Int,val id : String, var password: String,var applicationModel: ApplicationModel)  {

    fun isCorrectPassword(_password : String) : Boolean = password.equals(_password)

}