package restaurant
import geoclaseui.Geo
import user.*
import paymentMethod.*
import productAndMenu.*
import scala.Tuple2
import searcher.*
import java.beans.beancontext.BeanContextServiceAvailableEvent

class Restaurant(var code:Int, var name: String, var description: String,
                 var direcction:String, var geoLocation:Geo ) {
    var availablePaymentMethods: MutableCollection<PaymentMethod> = mutableListOf<PaymentMethod>()
    var products: MutableMap<Int, Product> = mutableMapOf<Int, Product>()
    var menus: MutableMap<Int, Menu> = mutableMapOf<Int, Menu>();
    var supervisor: Supervisor? = null
    var searcher: Searcher<Menu> = Searcher();

    fun menus():MutableMap<Int, Menu>{
        return menus;
    }

    fun addSupervisor(newSupervisor: Supervisor){
        supervisor= newSupervisor;

    }
    fun changeSupervisor(newSupervidor:Supervisor){
        supervisor = newSupervidor;
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
    //ver esto que onda, ver si es necesario ver si exite antes de borrar o lo hace la fucion remove.
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

}