package user
import applicationModel.MorfApp
import geoclase.Geo
import paymentMethod.*
import restaurant.Restaurant
import org.junit.Assert
import org.junit.Test
import java.util.*

class TestUser {

    private var applicationModel : MorfApp = MorfApp
    private var geoLocation1: Geo = Geo(1.2,2.2)
    private var cash : PaymentMethod = Cash()
    private var listOfPaymentMethod : MutableList<PaymentMethod> = mutableListOf(cash)
    private var restaurant : Restaurant = applicationModel.createRestaurant("El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1, listOfPaymentMethod)
    private var date = Date()
    private  var client: Client = Client(2, "Pepe","Pepe", "Roque saenz peña", geoLocation1, "1212", "mail@asd.com")

    @Test
    fun  newClient(){
        Assert.assertEquals("Roque saenz peña", client.address)
        Assert.assertEquals("Pepe",client.name)
    }
    @Test
    fun  newClientDirection(){
         Assert.assertEquals("Roque saenz peña", client.address)
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

    @Test
    fun  clientCanCreateOrdersAndSaveThem(){
        client.makeNewOrder(restaurant, mutableListOf(), Cash())
        Assert.assertEquals("Roque saenz peña", client.address)
        Assert.assertEquals("Pepe",client.name)
    }

    @Test
    fun  thePasswordIsCorrect(){
        Assert.assertTrue(client.isCorrectPassword("1212"))
    }

    @Test
    fun  thePasswordIsNotCorrect(){
        Assert.assertFalse(client.isCorrectPassword("Mustafa"))
    }
}