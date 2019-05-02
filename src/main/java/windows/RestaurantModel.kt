package windows

import applicationModel.MorfApp
import com.sun.prism.impl.PrismSettings
import discount.Discount
import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant
import scala.Mutable


@Observable
class RestaurantModel() {

    var restaurant: Restaurant? = null;
    lateinit var name: String;
    var products: MutableMap<Int, Product> = mutableMapOf();
    var menus: MutableMap<Int, Menu> = mutableMapOf();
    var menusOfProduct: MutableList<MenuModel>? = null;


    //busca por codigo de producto
    fun menusOfProduct(code: Int?):MutableList<MenuModel>?{
        //var  menuModelList = mutableListOf<MenuModel>();
        var menuList:MutableList<Menu>?= restaurant?.menusOfProduct(code);
        return transformListOfMenusToMenuModels(menuList);
    }

    fun getPriceOfMenuWithCode(code:Int):Double?{
       return  menus[code]?.totalPrice()
    }

    fun transformToProductModel(): MutableList<ProductModel>{
        var products = mutableListOf<Product>()
        this.restaurant?.products?.forEach { product-> products.add(product.value)}
        return this.transformListOfProductsToModel(products)

    }

    fun transformListOfProductsToModel(listOfProducts:MutableList<Product>?):MutableList<ProductModel>{

        var productsModel = mutableListOf<ProductModel>();

        listOfProducts?.forEach { product ->
            var tempProduct = ProductModel(this);
            tempProduct.code = product.code;
            tempProduct.name = product.name;
            tempProduct.description = product.description;
            tempProduct.price = product.price;
            tempProduct.category = product.category;
            productsModel.add(tempProduct) }

        return productsModel

    }

    fun transformListOfMenusToMenuModels(menuList: MutableList<Menu>?):MutableList<MenuModel>?{
        var menusModel = mutableListOf<MenuModel>();
        if (!menuList.isNullOrEmpty()) {
            menuList.forEach { menu ->
                var tempMenu = MenuModel(this)
                tempMenu.code = menu.code;
                tempMenu.name = menu.name;
                tempMenu.description = menu.description;
                tempMenu.price = menu.totalPrice();
                tempMenu.totalWithDiscount = menu.costAutocalculation();
                tempMenu.discount = this.transformToDiscountModel(menu.discount);
                tempMenu.productsOfMenu = this.transformListOfProductsToModel(menu.productsOfMenu);
                menusModel.add(tempMenu)
            }
        }
            return menusModel

     }

    private fun transformToDiscountModel(discount: Discount): DiscountModel {

        return DiscountModel(discount);
    }

    fun transformToMenuModel(): MutableList<MenuModel>?{
        var menusInRestaurant1 = mutableListOf<Menu>()
        this.restaurant?.menus?.forEach { menu-> menusInRestaurant1.add(menu.value) }
        return this.transformListOfMenusToMenuModels(menusInRestaurant1)
    }

    fun transformListOfProductModelToProduct(productModelList: MutableList<ProductModel>): MutableList<Product>{
        var tempProductList = mutableListOf<Product>();
        productModelList.forEach { tempProductList.add( this.restaurant?.products!!.getValue(it.code)) }

        return tempProductList;
    }

}