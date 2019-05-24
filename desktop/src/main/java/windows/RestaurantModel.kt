package windows

import discount.Discount
import discount.FixedDiscount
import discount.NoDiscount
import discount.PercentageDiscount
import org.uqbar.commons.model.annotations.Observable
import productAndMenu.Menu
import productAndMenu.Product
import restaurant.Restaurant

@Observable
class RestaurantModel {
    var restaurant: Restaurant? = null
    lateinit var name: String
    var products: MutableMap<Int, Product> = mutableMapOf()
    var menus: MutableMap<Int, Menu> = mutableMapOf()
    var menusOfProduct: MutableList<MenuModel>? = null
    var availableDiscounts: MutableList<DiscountModel> = transformListOfDiscountToDiscountModel(mutableListOf(
                                                            NoDiscount(),
                                                            FixedDiscount(0.0),
                                                            PercentageDiscount(0.0)))


    fun menusOfProduct(code: Int?):MutableList<MenuModel>?{
        var menuList:MutableList<Menu>?= restaurant?.menusOfProduct(code)
        return transformListOfMenusToMenuModels(menuList)
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

        var productsModel = mutableListOf<ProductModel>()

        listOfProducts?.forEach { product ->
            var tempProduct = ProductModel(this)
            tempProduct.code = product.code
            tempProduct.name = product.name
            tempProduct.description = product.description
            tempProduct.price = product.price
            tempProduct.category = product.category
            tempProduct.newProduct = false
            productsModel.add(tempProduct) }

        return productsModel
    }

    private fun getCorrectObservableBoolean(tempList: MutableList<ObservableBoolean>, bool: Boolean): ObservableBoolean?{
        return tempList.find { it.getValue == bool }
    }

    fun transformToMenuModel(): MutableList<MenuModel>?{
        var menusInRestaurant1 = mutableListOf<Menu>()
        this.restaurant?.menus?.forEach { menu-> menusInRestaurant1.add(menu.value) }
        return this.transformListOfMenusToMenuModels(menusInRestaurant1)
    }

    fun transformListOfMenusToMenuModels(menuList: MutableList<Menu>?):MutableList<MenuModel>?{
        var menusModel = mutableListOf<MenuModel>()
    //    if (!menuList.isNullOrEmpty()) {
            menuList?.forEach { menu ->
                var tempMenu = MenuModel(this)
                tempMenu.code = menu.code
                tempMenu.name = menu.name
                tempMenu.description = menu.description
                tempMenu.price = menu.totalPrice()
                tempMenu.totalWithDiscount = menu.costAutocalculation()
                tempMenu.discount = this.getCorrectDiscountModel(tempMenu.discounts, menu.discount)!!
                tempMenu.discountValue = tempMenu.discount.value
                tempMenu.productsOfMenu = this.transformListOfProductsToModel(menu.productsOfMenu)
                tempMenu.enabled = this.getCorrectObservableBoolean(tempMenu.observableBooleans, menu.enabled)!!
                tempMenu.newMenu = false
                menusModel.add(tempMenu)
      //      }
        }
        return menusModel

    }
    private fun getCorrectDiscountModel(tempList: MutableList<DiscountModel>, discount: Discount): DiscountModel? {

        var tempDiscount = tempList.find{ it.name == discount.name}
        tempDiscount?.value = discount.value
        tempDiscount?.discount = discount
        return tempDiscount
    }

    fun transformListOfProductModelToProduct(productModelList: MutableList<ProductModel>): MutableList<Product>{
        var tempProductList = mutableListOf<Product>()
        productModelList.forEach { tempProductList.add( this.restaurant?.products!!.getValue(it.code)) }

        return tempProductList
    }

    fun transformListOfDiscountToDiscountModel(discountList: MutableList<Discount>): MutableList<DiscountModel>{
        var tempDiscountList = mutableListOf<DiscountModel>()
        discountList.forEach { var tempDiscount = DiscountModel(it)
            tempDiscountList.add(tempDiscount)
        }
        return tempDiscountList
    }
}