package restaurant
import geoclaseui.Geo
import user.*
import paymentMethod.*
import productAndMenu.*
import scala.Tuple2
import java.awt.SystemColor.menu
import java.beans.beancontext.BeanContextServiceAvailableEvent

class Restaurant(var code:Int, var name: String, var description: String, var supervisor: Supervisor,
                 var direcction:String, var geoLocation:Geo,var availablePaymentMethods: MutableList<PaymentMethod> = mutableListOf<PaymentMethod>(),
                 var products: MutableSet<Product> = mutableSetOf<Product>(),var menus: MutableSet<Menu> = mutableSetOf<Menu>()) {


    fun changeSupervisor(newSupervidor:Supervisor){
        supervisor = newSupervidor;
    }

    fun addProductToStock(newProduct:Product){
        products.add(newProduct);
    }

    fun removeProductToStock(productToRemove: Product){
        products.remove(productToRemove);
    }
    fun addPaymentMethod(newPaymentMethod: PaymentMethod){
        availablePaymentMethods.add(newPaymentMethod);
    }
    fun removePaymentMethod(payment: PaymentMethod){
        availablePaymentMethods.remove(payment);
    }
    fun addMenu(newMenu: Menu){
        menus.add(newMenu);
    }

    //ver esto que onda, ver si es necesario ver si exite antes de borrar o lo hace la fucion remove.
    fun removeMenu(menuToRemove:Menu){
        menus.remove(menuToRemove);
    }

    fun menusAvailable():List<Menu>{
       return menus.filter {m -> m.enabled}
    }


}