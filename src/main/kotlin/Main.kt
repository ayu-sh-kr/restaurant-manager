package org.example

import org.example.algorithms.IntMergerSort
import org.example.algorithms.bubbleSort
import org.example.customer.domain.Customer
import org.example.order.domain.Item

val sort: (arr: MutableList<Int>) -> Unit = { arr ->

    if(arr.size <= 10) {
        println("Using bubble sort as an implementation provider")
        bubbleSort(arr)
    } else {
        println("Using merge sort as an implementation provider")
        val provider = IntMergerSort(arr)
        provider.sort()
    }
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}


fun main() {
    var customer = Customer(
        name = "Ayush Kumar Jaiswal",
        email = "akjaiswal2003@gmail.com",
        code = "83",
        phone = "8931007054"
    )

    val (_, orderAddItemUseCase, orderItemRemoveUseCase, orderCreateUseCase, orderFetchSingleUseCase, orderFetchAllUseCase) = bootstrapOrderDomain()

    val order = orderCreateUseCase.execute(customer.id, mutableListOf(
        Item("Paneer Lababdar", 130.0, 1),
        Item("Butter Naan", 30.0, 2)
    ))

    println(order)

    println("Order Total: OrderId=${order.id} Amount: ${order.getTotal()}")
}