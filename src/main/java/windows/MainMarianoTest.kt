package windows

fun main() {

    var apMod = ApplicationModel(UserModel());
    var ham = ProductModel();
    ham.name = "Hamburguesa";
    ham.price = 100.0;
    var coca = ProductModel();
    coca.name = "Coca Cola";
    coca.price = 60.0;

    var menu1 = MenuModel();
    menu1.name = "menu1";
    menu1.description = "menu1";



    //apMod.products.add(ham);
    //apMod.products.add(coca);
    apMod.menus.add(menu1);
    ApplicationWindow(apMod).startApplication();
}
