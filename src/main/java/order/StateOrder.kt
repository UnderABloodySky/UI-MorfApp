package statesOrder

enum class StateOrder(private val the_name: String, private var canChange : Boolean = false) {
    DELIVERED("Delivered"),
    PENDING("Pending", true),
    ONMYWAY("OnMyWay"),
    CANCELLED("Cancelled");

    fun canChange (): Boolean = canChange
}