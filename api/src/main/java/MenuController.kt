package api

import discount.Discount
import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus.*
import productAndMenu.Category
import productAndMenu.Menu


data class MenuData(val code: Int, var name : String, var description : String, var grossPrice : Double, var netPrice : Double,
                    var listOfProducts : MutableList<Product> ,var restaurantName : String, var discount : Discount, var enabled : Boolean, var img : String)

data class Product(var code: Int,
                   var name: String,
                   var description: String,
                   var price: Double,
                   var category: Category,
                   var productImage: String)

    class MenuControllerContext {
        private var lastId = 0;
        private val products = mutableListOf<Product>()
        private val menus = mutableListOf<MenuData>()

        /** CRUD **/

        fun addDataMenu(menu : Menu) : MenuData{
            var dataMenu = MenuData(menu.code, menu.name, menu.description, menu.totalPrice(),
                                    menu.costAutocalculation(), this.transformsProducts(menu.productsOfMenu),
                                    menu.restaurant.name,  menu.discount, menu.enabled, menu.menuImage                              )
            menus.add(dataMenu)
            return dataMenu
        }

        private fun transformsProducts(list : MutableList<productAndMenu.Product>) : MutableList<Product>{
            var res = mutableListOf<Product>()
            for (prod in list){
                var dataProduct = Product(prod.code, prod.name, prod.description, prod.price, prod.category, prod.productImage)
                res.add(dataProduct)
            }
            return res
        }

        fun getMenus(ctx : Context){
            ctx.json(this.menus)
        }

        fun getAll(ctx: Context) {
            ctx.json(this.products)
        }

        fun getProduct(ctx: Context) {
            val code = ctx.pathParam("code").toInt()
            ctx.json(getProductById(code))
        }

        fun addModelProduct(prod : productAndMenu.Product){
            var dataProduct = Product(prod.code, prod.name, prod.description, prod.price, prod.category, prod.productImage)
            products.add(dataProduct)
        }

        fun addProduct(ctx: Context) {
            val product = ctx.body<Product>()
            ctx.status(CREATED_201)
            ctx.json(addProduct(product))
        }

        fun updateProduct(ctx: Context) {
            val code = ctx.pathParam("code").toInt()
            val viewProduct = ctx.body<Product>()
            val oldProduct = getProductById(code)
            val newProduct = Product(oldProduct.code,
                                    viewProduct.name,
                                    viewProduct.description,
                                    viewProduct.price,
                                    viewProduct.category,
                                    viewProduct.productImage)

            products.remove(oldProduct)
            products.add(newProduct)
            ctx.json(newProduct)
        }

        fun deleteProduct(ctx: Context) {
            val code = ctx.pathParam("code").toInt()
            products.remove(getProductById(code))
            ctx.status(NO_CONTENT_204)
        }

        /** Support Functions **/

        private fun getProductById(code: Int): Product {
            return products.firstOrNull { it.code == code }
                    ?: throw NotFoundResponse("No se encontró el producto con id $code")
        }

        fun addProduct(product: Product): Product{
            val product = Product(++this.lastId,
                                    product.name,
                                    product.description,
                                    product.price,
                                    product.category,
                                    product.productImage)

            products.add(product)
            return product
        }
    }
