package user
import geoclaseui.Geo
import user.*
import paymentMethod.*
import productAndMenu.*
import restaurant.Restaurant
import org.junit.Assert
import org.junit.Test

class TestUser {

    private var geoLocation1: Geo = Geo(1.2,2.2);

    private var restaurantSinArticulos: Restaurant(1, "El Tano", "por quilmes oeste", var geoLocation:Geo, mutableListOf<PaymentMethod>(),
    mutableSetOf<Product>(),mutableSetOf<Menu>());
    private var applicationModel = ApplicationModel ;
    private var supervisor : Supervisor = Supervisor(1, restaurantSinArticulos, "123454", applicationModel)
    var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);


//test crear un cliente,
    @Test
    fun  newClient(){
         private var client: Client = Client(1,"Roque saenz peña", 10/2/19,geoLocation1,  "1212", applicationMode)
         Assert.assertEquals("Roque saenz peña", client.address);
         Assert.assertEquals(1,id)
    }

    //un cliente puede generar una orden y se agrega a la ordenes realizadas
    @Test
    fun  clientCanCreateOrdersAndSaveThem(){
        private var client: Client = Client(1,"Roque saenz peña", 10/2/19,geoLocation1,  "1212", applicationMode)

        client.makeNewOrder(restaurantSinArticulos, )
        Assert.("Roque saenz peña", client.address);
        Assert.assertEquals(1,id)
    }

//crear un supervisor






}