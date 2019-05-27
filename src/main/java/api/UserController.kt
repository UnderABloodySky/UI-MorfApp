package controllers

import applicationModel.MorfApp
import geoclase.Geo
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus.CREATED_201
import org.eclipse.jetty.http.HttpStatus.NO_CONTENT_204
import user.Client

data class DataUser(private var user : Client) {
    var code = user.code
    var id = user.id
    var name = user.name
    var address = user.address
    var email = user.email
    var pendingOrders = user.pendingOrders
    var historicOrders = user.historicOrders

}

/*
fun update(id: Int, user: User) {
    users.put(id, User(name = user.name, email = user.email, id = id))
}
*/

class UserControllerContext {

   private val users = HashMap<String, DataUser>()
   private val morfApp = MorfApp

        // CRUD
        //Ok
        fun getAllUsers(ctx: Context) {
            ctx.json(users)
        }

        //Ok
        fun findUser(ctx: Context) {
            val id = ctx.pathParam("id")
            val res = getUserById(id)
            ctx.json(res)
        }

        fun findUser2(ctx: Context) {
            val id = ctx.pathParam("id")
            val res = getUserById(id)
            ctx.json(res)
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
                val geo = Geo(2.0, 2.0)
                val email = ctx.formParam("email") as String
                val newUser = morfApp.createClient(id, name, address, geo, pass, email)
                ctx.status(CREATED_201)
                ctx.json(addDataUser(newUser))
            }
        }

        private fun canRead(ctx: Context) : Boolean = ctx.anyFormParamNull("id", "name","lastName","address", "pass", "email")

    /*
        //Preguntar TOKEN
        fun updateUser(ctx: Context) {
            val id = ctx.pathParam("id").toInt()
            val newGeo = ctx.body<Geo>()
            val oldPlace = getPlaceById(id)
            val newPlace = Place(oldPlace.id, newGeo.name, newGeo.latitude, newGeo.longitude)
            places.remove(oldPlace)
            places.add(newPlace)
            ctx.json(newPlace)
        }
    */

        //Preguntar TOKEN
        fun deleteDataUser(ctx: Context) {
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



