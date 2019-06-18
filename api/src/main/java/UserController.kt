package api

import api.OrderData
import api.UserNameInUseException
import applicationModel.MorfApp
import com.fasterxml.jackson.annotation.JsonIgnore
import exception.NoUserAuthenticateException
import geoclase.Geo
import io.javalin.BadRequestResponse
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus.CREATED_201
import org.eclipse.jetty.http.HttpStatus.NO_CONTENT_204
import user.Client

//Login
data class LittleUser(var id : String, var password : String)

//Register
data class PseudoUser(val id : String, val name : String, var password : String, var address : String , var longitude : Double, var latitude : Double , val email : String)

//De la vista
data class DataUser(@JsonIgnore val client : Client){
    val code = client.code
    val id = client.id
    val name = client.id
    var address = client.address
    var geoLocation = client.geoLocation
    val email = client.email
    var pendingOrders = mutableListOf<OrderData>()
    var historicOrders = mutableListOf<OrderData>()

    private fun addOrderDataTo(aOrder : OrderData, aList : MutableList<OrderData>){
        aList.add(aOrder)
    }

    fun addOrderToPending(aOrder : OrderData){
        addOrderDataTo(aOrder, pendingOrders)
    }

    fun addOrderToHistoric(aOrder : OrderData){
        addOrderDataTo(aOrder, pendingOrders)
    }
}

data class MiddleUser(val code : Int, val id : String, val name : String, val address : String, val geoLocation: Geo, val email : String)

class UserController {
   private val users = HashMap<String, DataUser>()
   private val morfApp = MorfApp

        fun login(ctx : Context){
            val data = ctx.body<LittleUser>()
            try{
                 morfApp.authenticate(data.id, data.password)
            }
            catch (e : NoUserAuthenticateException){
                throw NotFoundResponse(e.message as String)
            }
            ctx.status(200)
            ctx.json(getUserById(data.id))
        }

        fun getAllUsers(ctx: Context) {
            ctx.json(users)
        }

        fun findUser(ctx: Context) {
            val id = ctx.pathParam("id")
            ctx.json(getUserById(id))
        }

        fun findUser2(ctx: Context) {
            val id = ctx.pathParam("id")
            val dataUser = getUserById(id)
            val plus = ctx.queryParam("include")
            if(plus == "orders") {
                ctx.json(dataUser)
            }
            else{
                ctx.json(MiddleUser(dataUser.code, id, dataUser.name, dataUser.address, dataUser.geoLocation, dataUser.email))
            }
        }

        fun findUserByMail(ctx : Context){
            val email = ctx.pathParam("email")
            ctx.json(getUserByEmail(email))
        }

       fun addUser(ctx: Context){
            val user = ctx.body<PseudoUser>()
            val id = user.id
            try{
                validate(id)
            }
            catch(e : UserNameInUseException) {
                throw NotFoundResponse(e.message as String)
            }

            val name = user.name
            val address = user.address
            val pass = user.password
            val geo = Geo(user.latitude, user.longitude, user.address)
            val email = user.email
            val newUser = morfApp.createClient(id, name, address, geo, pass, email)
            ctx.status(CREATED_201)
            ctx.json(addDataUser(newUser))
        }

        private fun validate(id : String){
            if(!isCorrectId(id)){
                throw UserNameInUseException("El nombre de usuario ya esta en uso")
            }
        }

        fun addUserByForm(ctx: Context) {
            if (canRead(ctx)) {
                val id = ctx.formParam("id") as String
                validate(id)
                val name = ctx.formParam("name") + " " + ctx.formParam("lastName") as String
                val address = ctx.formParam("address") as String
                val pass = ctx.formParam("pass") as String
                val geo = Geo(ctx.formParam("latitude")!!.toDouble(), ctx.formParam("longitude")!!.toDouble())
                val email = ctx.formParam("email") as String
                val newUser = morfApp.createClient(id, name, address, geo, pass, email)
                ctx.status(CREATED_201)
                ctx.json(addDataUser(newUser))
            }
            else{
                throw BadRequestResponse("Ningun campo del formulario puede estar vacio")
            }
        }

       fun updateUser(ctx: Context) {
            val id = ctx.pathParam("id")

            val newPseudoUser = ctx.body<PseudoUser>()
            val client = morfApp.findUser(id) as Client
            //Falta updatear client modelo con la informacion del newPseudoUser
            users.remove(id)
            ctx.json(addDataUser(client))
        }

        fun deleteUser(ctx: Context) {
            val id = ctx.pathParam("id")
            //Lo borro de la vista, no del modelo.
            users.remove(id)
            ctx.status(NO_CONTENT_204)
        }

        private fun getUserByEmail(email: String): DataUser {
            return users.values.firstOrNull { it.email == email }
                ?: throw NotFoundResponse("$email no es un mail registrado")
        }

        private fun getUserById(id : String): DataUser {
           return users.get(id) ?: throw NotFoundResponse("No se encontró el usuario $id")
        }

        private fun canRead(ctx: Context) : Boolean = !ctx.anyFormParamNull("id", "name","lastName","address", "pass", "email")

        private fun isCorrectId(id : String) : Boolean = !users.containsKey(id)

        fun addDataUser(client : Client) : DataUser{
            val dataUser = DataUser(client)
            users.put(dataUser.id, dataUser)
            return dataUser
        }
}




