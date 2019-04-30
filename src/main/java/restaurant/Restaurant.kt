package restaurant
import applicationModel.MenuFactory
import applicationModel.ProductFactory
import discount.Discount
//import exception.AnyMenuContainsThisProductException
import geoclaseui.Geo
import order.Order
import org.uqbar.commons.model.Search
import user.*
import paymentMethod.*
import productAndMenu.*
import searcher.*

class Restaurant(code : Int, name : String, description : String,
                 var direcction : String, var geoLocation : Geo, var availablePaymentMethods : MutableCollection<PaymentMethod>) : Searchable(code, name, description){

    var products: MutableMap<Int, Product> = mutableMapOf()
    var menus: MutableMap<Int, Menu> = mutableMapOf()
    lateinit var supervisor : Supervisor
    var orders : MutableCollection<Order> = mutableListOf()
    var searcher: Searcher = Searcher()
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

    fun editMenu(code: Int,
                 name: String,
                 description: String,
                 productList: MutableList<Product>,
                 discount: Discount,
                 enabled: Boolean){

        this.menus.getValue(code).name = name;
        this.menus.getValue(code).description = description;
        this.menus.getValue(code).productsOfMenu = productList;
        this.menus.getValue(code).discount = discount;
        this.menus.getValue(code).enabled = enabled;
    }

    fun deleteMenu(code: Int){
        this.menus.remove(code);
    }

    fun createMenu(name: String,
                   description: String,
                   products: MutableList<Product>,
                   restaurant: Restaurant,
                   discount: Discount,
                   enabled: Boolean): Menu{

        var newMenu: Menu = menuFactory.createMenu(name, description, products, restaurant, discount, enabled)
        addMenu(newMenu);
        return newMenu;
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
       return menus.filter {m -> m.value.enabled} as Map<Int, Menu>
    }

    private fun asSearchable() : MutableMap<Int, Searchable> = this.menus as MutableMap<Int, Searchable>
	

    fun findMenuesPair(criteria: CriteriaByString): Pair<MutableCollection<Menu?>, Restaurant>{
        var pairList = mutableListOf<Menu?>();
        criteria.search(asSearchable())
                .forEach { pairList.add(it as Menu) };
        return Pair(pairList, this)
    }

    fun findMenu(criteria : Criteria) : MutableCollection<Menu?>{
        return criteria.search(asSearchable()) as MutableCollection<Menu?>
    }

    fun addOrder(order: Order) : Unit {
        orders.add(order)
    }

    fun menusOfProduct(code:Int?):MutableList<Menu> {
        var menusOfProduct = mutableListOf<Menu>();
        menus.forEach { menu->
                            if (menu.value.containProductWith(code))
                                menusOfProduct.add(menu.value);
                    }
        return menusOfProduct;

    }
    fun removeProductsFromMenus(code: Int){
        this.menus.forEach{
            it.component2().removeProductFromMenu(code);
        }
    }
}
