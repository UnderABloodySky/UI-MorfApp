package controllers

import data.DataUser
import geoclase.Geo
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus.CREATED_201
import org.eclipse.jetty.http.HttpStatus.NO_CONTENT_204
import user.Client


//Client(code, name, id, address, registrationDate, geoLocation,  password, applicationModel)

data class DataUser(private var user : Client) {
    var code = user.code
    var id = user.id
    var name = user.name
    var address = user.address
}

class MorfAppControllerContext {

   private val users = mutableListOf<DataUser>()

        // CRUD

        //Ok
        fun getAllUsers(ctx: Context) {
            ctx.json(users)
        }

        //Ok
        fun getUser(ctx: Context) {
            val id = ctx.pathParam("id")
	    print(id)	
            ctx.json(getUserById(id))
        }
/*
            fun addPlace(ctx: Context) {
                val geo = ctx.body<Geo>()
                ctx.status(CREATED_201)
           //     ctx.json(addGeoPlace(geo))
            }

           fun updatePlace(ctx: Context) {
                val id = ctx.pathParam("id").toInt()
                val newGeo = ctx.body<Geo>()
              //  val oldPlace = getPlaceById(id)
              //  val newPlace = Place(oldPlace.id, newGeo.name, newGeo.latitude, newGeo.longitude)
              //  places.remove(oldPlace)
              //  places.add(newPlace)
              //  ctx.json(newPlace)
            }
    */
        fun deletePlace(ctx: Context) {
            val id = ctx.pathParam("id").toInt()
          //  places.remove(getPlaceById(id))
            ctx.status(NO_CONTENT_204)
        }

        private fun getUserById(id : String): DataUser {
           return users.firstOrNull { it.id == id }
                    ?: throw NotFoundResponse("No se encontró el usuario buscado")
        }

        fun addDataUser(client : Client): DataUser {
            val dataClient = DataUser(client)
            users.add(dataClient)
            return dataClient
        }
}



