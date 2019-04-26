package windows

import applicationModel.MorfApp
import discount.NoDiscount
import geoclaseui.Geo
import paymentMethod.Cash
import productAndMenu.Category
import productAndMenu.Menu
import productAndMenu.Product

fun main() {
    var products = mutableSetOf<Product>();
    var menus = mutableSetOf<Menu>();
    var morfap = MorfApp;

    morfap.createRestaurant("La Conga",
            "Cocina Peruana",
            "Calle Falsa 123",
            Geo(1.5, 1.5),
            mutableListOf(Cash()),
            products,
            menus);

    var apMod = ApplicationModel(UserModel());
    var ham = Product(1, "Hamburguesa", "al vapor", 100.0, Category.NONE);
    var coca = Product(2, "Coca Cola", "Azucar 200%", 60.0, Category.DRINK);
    var menu1 = Menu(1,
                     "Menu1",
                     "Coca + Hambur",
                      mutableListOf(ham, coca),
                      morfap.restaurants.getValue(0),
                      NoDiscount(),
                      true);

    morfap.restaurants.getValue(0).products.put(ham.code, ham);
    morfap.restaurants.getValue(0).products.put(coca.code, coca);
    morfap.restaurants.getValue(0).menus.put(menu1.code, menu1);

    LoginWindow(UserModel()).startApplication();
//    ApplicationWindow(apMod).startApplication();
}
