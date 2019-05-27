package user
import applicationModel.*
import com.fasterxml.jackson.annotation.JsonIgnore


abstract class User( val code : Int, val id : String, val name : String, var password: String)  {
    var applicationModel = MorfApp
    fun isCorrectPassword(_password : String) = password == _password

}
