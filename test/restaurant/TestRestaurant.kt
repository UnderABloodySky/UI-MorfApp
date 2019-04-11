package restaurant

import applicationModel.ApplicationModel
import geoclaseui.Geo
import org.junit.Assert
import org.junit.Test
import productAndMenu.Menu
import productAndMenu.Product
import user.Supervisor
import java.util.*

private class TestRestaurant {

    private var geoLocation1: Geo = Geo(1.2,2.2);
    private var applicationModel : ApplicationModel = ApplicationModel ;
    private var date = Date()
    var newTestRestaurant: Restaurant = Restaurant(1,"El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1);
    private var supervisor : Supervisor = Supervisor(1, "CarloMagno",newTestRestaurant, "123454", applicationModel)
    private var menu = Menu(1,"SodaMenu","with authentic sodas since 90's", mutableListOf<Product>(),null,true);



    @Test
    fun newRestaurantIsCreated(){
        var emptyMenuList: MutableSet<Menu> = mutableSetOf<Menu>();
        var emptyProductList: MutableSet<Product> = mutableSetOf<Product>();
        var newTestRestaurant: Restaurant = Restaurant(1,"El Tano", "inserte descripcion", "por quilmes oeste", geoLocation1);
        Assert.assertEquals(newTestRestaurant.menus,emptyMenuList);
        Assert.assertEquals(newTestRestaurant.products,emptyProductList);
        Assert.assertEquals(newTestRestaurant.code,1);
        Assert.assertEquals(newTestRestaurant.name,"El Tano");
        Assert.assertEquals(newTestRestaurant.description,"inserte descripcion");
        Assert.assertEquals(newTestRestaurant.direcction,"por quilmes oeste");
        Assert.assertEquals(newTestRestaurant.geoLocation,geoLocation1);

    }

    

}