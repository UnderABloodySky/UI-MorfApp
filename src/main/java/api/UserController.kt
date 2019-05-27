package controllers

import applicationModel.MorfApp
import com.fasterxml.jackson.annotation.JsonCreator
import exception.NoUserAuthenticateException
import geoclase.Geo
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus.CREATED_201
import org.eclipse.jetty.http.HttpStatus.NO_CONTENT_204
import user.Client
import user.User

data class DataUser(private var user : Client) {
    var code = user.code
    var id = user.id
    var name = user.name
    var address = user.address
    var email = user.email
    var pendingOrders = user.pendingOrders
    var historicOrders = user.historicOrders
}

data class LittleUser(var aId : String, var aPass : String)


class UserControllerContext {

   private val users = HashMap<String, DataUser>()
   private val morfApp = MorfApp

        // CRUD

        fun login(ctx : Context){
            val data = ctx.body<LittleUser>()
            var validate = false
            try{
                morfApp.authenticate(data.aId, data.aPass)
                validate = true
            }

            catch (e : NoUserAuthenticateException){
                throw NotFoundResponse(e.message as String)
            }

            if(validate){
                ctx.status(200)
                ctx.json(getUserById(data.aId))
            }
        }

        fun getAllUsers(ctx: Context) {
            ctx.json(users)
        }

        fun findUser(ctx: Context) {
            val id = ctx.pathParam("id")
            val res = getUserById(id)
            ctx.json(res)
        }

        fun findUser2(ctx: Context) {
            val id = ctx.queryParam("id") as String
            val plus = ctx.queryParam("orders") as String
            var res = ctx.json(getUserById(id))
            if(id != null && plus ==  null){

            }
            else{
                findUser(ctx)
            }
            ctx.status(200)
        }

        fun findUserByMail(ctx : Context){
            val email = ctx.pathParam("email")
            ctx.json(getUserByEmail(email))
        }

       fun addUser2(ctx: Context){
            val user = ctx.body<Client>()
            val id = user.id
            val name = user.name
            val address = user.address
            val pass = user.password
            val geo = user.geoLocation
            val email = user.email
            val newUser = morfApp.createClient(id, name, address, geo, pass, email)
            ctx.status(CREATED_201)
            ctx.json(addDataUser(newUser))
        }

        fun addUser(ctx: Context) {
            if (!canRead(ctx)) {
                val id = ctx.formParam("id") as String
                val name = ctx.formParam("name") + " " + ctx.formParam("lastName") as String
                val address = ctx.formParam("address") as String
                val pass = ctx.formParam("pass") as String
                //Ver esto
                //val geo =  ctx.body<Geo>() as geoclaseui.Geo
                val geo = Geo(ctx.formParam("latitude")!!.toDouble(), ctx.formParam("longitude")!!.toDouble())
                val email = ctx.formParam("email") as String
                val newUser = morfApp.createClient(id, name, address, geo, pass, email)
                ctx.status(CREATED_201)
                ctx.json(addDataUser(newUser))
            }
        }

        private fun canRead(ctx: Context) : Boolean = ctx.anyFormParamNull("id", "name","lastName","address", "pass", "email")

        /*//Preguntar TOKEN
        fun updateUser(ctx: Context) {
            val id = ctx.pathParam("id")

            val newDataUser = ctx.body<Client>()
            val user = morfApp.findUser(id)


            users.remove(oldDataUser.id)
            users.put(newDataUser.id, newDataUser)
            ctx.json(newDataUser)
        }
        */

        //Preguntar TOKEN
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
           return users.values.firstOrNull { it.id == id }
                    ?: throw NotFoundResponse("No se encontró el usuario $id")
        }

        //Falta estas clases
        private fun isCorrectPassWord(pass : String) : Boolean = true

        private fun isCorrectId(id : String) : Boolean = true

        fun addDataUser(client : Client): DataUser {
            val dataClient = DataUser(client)
            users.put(dataClient.id, dataClient)
            return dataClient
        }
}




