package user
import applicationModel.ApplicationModel
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
    private var restaurantSinArticulos: Restaurant = Restaurant(1, "El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1, mutableListOf<PaymentMethod>(), mutableSetOf<Product>());
    private var applicationModel : ApplicationModel = ApplicationModel ;
    private var date = Date()
    private var supervisor : Supervisor = Supervisor(1, restaurantSinArticulos, "123454", applicationModel)
    private var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);
    private  var client: Client = Client(1,"Roque saenz peña", date,geoLocation1, "1212", applicationModel)


    //test crear un cliente,
    @Test
    fun  newClient(){
         Assert.assertEquals("Roque saenz peña", client.address);
         Assert.assertEquals(1,client.id)
    }

    //un cliente puede generar una orden y se agrega a la ordenes realizadas
    @Test
    fun  clientCanCreateOrdersAndSaveThem(){
        client.makeNewOrder(restaurantSinArticulos, mutableListOf(), Cash())
        Assert.assertEquals("Roque saenz peña", client.address);
        Assert.assertEquals(1,client.id)
    }

//crear un supervisor






}