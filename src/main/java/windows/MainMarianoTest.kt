package windows

import applicationModel.MorfApp
import discount.NoDiscount
import geoclaseui.Geo
import paymentMethod.Cash
import productAndMenu.Category
import productAndMenu.Menu
import productAndMenu.Product

fun main() {
    var products = mutableListOf<Product>();
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
                                         "Coca + Hambur", products, laConga, NoDiscount(), false)
    menu1.addProductToMenu(unaHamburguesaSalvaje)
    menu1.addProductToMenu(unaCocaSalvaje)

    var porcionDePapas : Product = laConga.createProduct("Papas fritas", "papas", 80.0, Category.ADITIONAL)
    var rabas : Product = laConga.createProduct("Rabas", "rabas fritas", 160.0, Category.STARTER)

    var menu2: Menu = laConga.createMenu("Menu2",
            "Coca + Hambur", products, laConga, NoDiscount(), true)

    menu2.addProductToMenu(rabas)
    menu2.addProductToMenu(porcionDePapas)

    laConga.supervisor.addMenuToRestaurant(menu2)
    laConga.supervisor.addMenuToRestaurant(menu1);

    LoginWindow(UserModel()).startApplication();


}
