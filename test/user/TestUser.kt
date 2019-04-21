package user
import applicationModel.ApplicationModel
import discount.NoDiscount
import geoclaseui.Geo
import user.*
import paymentMethod.*
import productAndMenu.*
import restaurant.Restaurant
import org.junit.Assert
import org.junit.Test
import java.util.*

class TestUser {

    private var geoLocation1: Geo = Geo(1.2,2.2);
    private var restaurantSinArticulos: Restaurant = Restaurant(1, "El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1);
    private var applicationModel : ApplicationModel = ApplicationModel ;
    private var date = Date()
    private var supervisor : Supervisor = Supervisor(1, "JulioCesar", restaurantSinArticulos, "123454", applicationModel)
    private var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(), NoDiscount(),true);
    private  var client: Client = Client(2, "Pepe","Roque saenz peña", date,geoLocation1, "1212", applicationModel)


    //test crear un cliente,

    @Test
    fun  newClient(){
        Assert.assertEquals("Roque saenz peña", client.address);
        Assert.assertEquals(1,client.name)
    }
    @Test
    fun  newClientDirection(){
         Assert.assertEquals("Roque saenz peña", client.address);
    }

    @Test
    fun  newClientId(){
        Assert.assertEquals("Pepe",client.name)
    }

    @Test
    fun  newClientCode(){
        Assert.assertEquals(2,client.code)
    }

    @Test
    fun  newClientDate(){
        Assert.assertEquals(date,client.registrationDate)
    }

    @Test
    fun  newClientPassword(){
        Assert.assertEquals("1212",client.password)
    }

    @Test
    fun  newClientGeoLocation(){
        Assert.assertEquals(geoLocation1,client.geoLocation)
    }

    @Test
    fun  newClientApplicationModel(){
        Assert.assertEquals(applicationModel,client.applicationModel)
    }

    //un cliente puede generar una orden y se agrega a la ordenes realizadas
    @Test
    fun  clientCanCreateOrdersAndSaveThem(){
        client.makeNewOrder(restaurantSinArticulos, mutableListOf(), Cash())
        Assert.assertEquals("Roque saenz peña", client.address);
        Assert.assertEquals("Pepe",client.name)
    }

//crear un supervisor

    @Test
    fun  thePasswordIsCorrect(){
        Assert.assertTrue(client.isCorrectPassword("1212"))
    }

    @Test
    fun  thePasswordIsNotCorrect(){
        Assert.assertFalse(client.isCorrectPassword("Mustafa"))
    }






}