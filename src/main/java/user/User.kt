package user
import applicationModel.*



abstract class User( val code : Int,val name : String, var password: String,var applicationModel: ApplicationModel)  {

    fun isCorrectPassword(_password : String) : Boolean{

        return password.equals(_password)
    }

}