package productAndMenu

import searcher.Searchable

class Product(code: Int,
              name: String,
              description: String,
              var price: Double,
              var category: Category) : Searchable(code, name, description) {

    var productImage : String = ""
}
