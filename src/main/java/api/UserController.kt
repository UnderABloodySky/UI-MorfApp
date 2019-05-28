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
    var geoLocation = user.geoLocation
    var pendingOrders = user.pendingOrders
    var historicOrders = user.historicOrders
}

data class LittleUser(var id : String, var pass : String)


class UserControllerContext {

   private val users = HashMap<String, DataUser>()
   private val morfApp = MorfApp

        // CRUD
        fun login(ctx : Context){
            val data = ctx.body<LittleUser>()
            try{
                 morfApp.authenticate(data.id, data.pass)
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
            val id = ctx.queryParam(":id")
            //val plus = ctx.queryParam("orders")
            var res = ctx.json(getUserById(id!!))
            print(res)

         /*   if(id != null && plus ==  null){
                //res
            }
            else{
                findUser(ctx)
            }
          */
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
                val geo = Geo(ctx.formParam("latitude")!!.toDouble(), ctx.formParam("longitude")!!.toDouble())
                val email = ctx.formParam("email") as String
                val newUser = morfApp.createClient(id, name, address, geo, pass, email)
                ctx.status(CREATED_201)
                ctx.json(addDataUser(newUser))
            }
        }

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

        private fun canRead(ctx: Context) : Boolean = ctx.anyFormParamNull("id", "name","lastName","address", "pass", "email")

        private fun isCorrectId(id : String) : Boolean = !users.containsKey(id)

        fun addDataUser(client : Client): DataUser {
            val dataClient = DataUser(client)
            users.put(dataClient.id, dataClient)
            return dataClient
        }
}




