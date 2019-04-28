package restaurant
import applicationModel.MenuFactory
import applicationModel.ProductFactory
import discount.Discount
import geoclaseui.Geo
import order.Order
import user.*
import paymentMethod.*
import productAndMenu.*
import searcher.*

class Restaurant(var code:Int, var name: String, var description: String,
                 var direcction:String, var geoLocation:Geo, var availablePaymentMethods : MutableCollection<PaymentMethod>) {

    var products: MutableMap<Int, Product> = mutableMapOf()
    var menus: MutableMap<Int, Menu> = mutableMapOf()
    lateinit var supervisor : Supervisor
    var orders : MutableCollection<Order> = mutableListOf()
    var searcher: Searcher<Menu> = Searcher()
    private var productFactory : ProductFactory = ProductFactory()
    private var menuFactory : MenuFactory = MenuFactory()

    fun menus():MutableMap<Int, Menu>{
        return menus;
    }

    fun addSupervisor(newSupervisor: Supervisor){
        supervisor= newSupervisor;
    }

    fun createProduct(name : String, description : String, price : Double, category : Category) : Product {
        var newProduct : Product = productFactory.createProduct(name, description, price, category)
        addProductToStock(newProduct)
        return newProduct
    }
    fun editProduct(code: Int, name : String, description : String, price : Double, category : Category) {
        this.products.getValue(code).name = name;
        this.products.getValue(code).description = description;
        this.products.getValue(code).price = price;
        this.products.getValue(code).category = category;
    }
    fun deleteProduct(code: Int){
        this.products.remove(code);
    }

    fun createMenu(name : String, description : String, products : MutableCollection<Product>, restaurant : Restaurant, discount : Discount, enabled : Boolean) : Menu{
        var newMenu : Menu = menuFactory.createMenu(name, description, products, restaurant, discount, enabled)
        addMenu(newMenu)
        return newMenu
    }

    fun addProductToStock(newProduct:Product){
        products.put(newProduct.code, newProduct);
    }

    fun removeProductFromStock(productToRemove: Product){
        products.remove(productToRemove.code);
    }

    fun productIsContainedInTheStock(product: Product):Boolean{
        return products.containsValue(product);
    }

    fun addPaymentMethod(newPaymentMethod: PaymentMethod){
        availablePaymentMethods.add(newPaymentMethod);
    }
    fun removePaymentMethod(payment: PaymentMethod){
        availablePaymentMethods.remove(payment);
    }
    fun addMenu(newMenu: Menu){
        menus.put(newMenu.code, newMenu);
    }

    fun menuIsRegistered(menu: Menu):Boolean{
        return menus.containsValue(menu);
    }

    fun removeMenu(menuToRemove:Menu){
        menus.remove(menuToRemove.code);
    }

    fun menusAvailable() : Map<Int, Menu>{
       return menus.filter {m -> m.value.enabled}
    }

    fun findMenuesPair(criteria: CriteriaByString<Menu>): MutableList<Pair<Menu?, Restaurant>>{
        var pairList = mutableListOf<Pair<Menu?, Restaurant>>();
        criteria.search(this.menus)
                .forEach { pairList.add(Pair(it, this)) };
        return pairList;
    }

    fun findMenu(criteria : Criteria<Menu>) : MutableList<Menu?>{
        return criteria.search(this.menus);
    }

    fun addOrder(order: Order) : Unit {
        orders.add(order)
    }

    //ojo ver que si esta vacio tiene que levantar una excepcion
    fun menusOfProduct(code:Int?):MutableList<Menu> {
        var menusOfProduct = mutableListOf<Menu>();

        for (menu in menus) {
            if (menu.value.containProductWith(code)) {
                menusOfProduct.add(menu.value);
                }
        }
        return menusOfProduct;
    }
}