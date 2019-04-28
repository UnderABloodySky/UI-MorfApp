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
    var laConga = morfap.createRestaurant("La Conga",
            "Cocina Peruana",
            "Calle Falsa 123",
            Geo(1.5, 1.5),
            mutableListOf(Cash()));
    morfap.createSupervisor(laConga, "...", "...");

    var unaHamburguesaSalvaje : Product = laConga.createProduct("Hamburguesa", "Al vapor", 100.0, Category.NONE)
    var unaCocaSalvaje : Product = laConga.createProduct("Coca Cola", "Azucar 200%", 60.0, Category.DRINK)
    var menu1: Menu = laConga.createMenu("Menu1",
                                         "Coca + Hambur", laConga.products.values, laConga, NoDiscount(), true)


    LoginWindow(UserModel()).startApplication();


}
