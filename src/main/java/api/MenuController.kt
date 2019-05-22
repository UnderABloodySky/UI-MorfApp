package api

import io.javalin.Context
import io.javalin.NotFoundResponse
import org.eclipse.jetty.http.HttpStatus.*
import productAndMenu.Category

data class Product(var code: Int,
                   var name: String,
                   var description: String,
                   var price: Double,
                   var category: Category){}

data class Menu(var code: Int){}

    class MenuControllerContext {
        private var lastId = 0;
        private val products = mutableListOf<Product>()

        /** CRUD **/

        fun getAll(ctx: Context) {
            ctx.json(this.products)
        }

        fun getProduct(ctx: Context) {
            val code = ctx.pathParam("code").toInt()
            ctx.json(getProductById(code))
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
                                    viewProduct.category)

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
                                    product.category)

            products.add(product)
            return product
        }
    }
