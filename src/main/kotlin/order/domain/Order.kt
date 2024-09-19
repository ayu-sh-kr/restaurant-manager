package org.example.order.domain

import org.example.customer.domain.CustomerId
import java.util.Date
import java.util.UUID

data class OrderId(val value: UUID)

class Order {
    val id = OrderId(UUID.randomUUID())
    val customerId: CustomerId
    val items = mutableListOf<Item>()
    val date = Date()

    constructor(customerId: CustomerId, items: MutableList<Item> = mutableListOf<Item>()) {
        this.customerId = customerId
        this.items.addAll(items)
    }

    fun addItem(item: Item) {
        this.items.add(item)
    }

    fun removeItem(itemId: ItemId) {
        items.removeIf {item -> item.id == itemId}
    }

    fun getTotal(): Double {
        var total: Double = 0.0
        items.forEach { item -> total += item.getTotal() }
        return total
    }

    override fun toString(): String {
        return "Order(\n\tid: ${this.id}, \n\tcustomerId=$customerId, \n\titems=$items, \n\tdate=$date\n)"
    }
}