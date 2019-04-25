package windows

fun main() {
    var apMod = ApplicationModel();
    var pM = ProductModel();
    pM.name = "pepe";
    pM.price = 100.0;

    apMod.products.add(pM);
    ApplicationWindow(apMod).startApplication();
}
