package org.example.order.domain

import org.example.Repository

@Repository
class OrderRepository(val data: MutableList<Order>) {

    fun save(order: Order): Order {
        data.add(order)
        return order
    }

    fun existsById(id: OrderId): Boolean {
        return this.data.find { order -> order.id == id } != null
    }

    fun findById(id: OrderId): Order? {
        return this.data.find { order -> order.id == id }
    }

    fun update(order: Order, id: OrderId) {
        if (data.removeIf { it.id == order.id }) {
            data.add(order)
        }
    }

    fun findAll(): MutableList<Order> {
        return this.data.map { it -> it }.toMutableList()
    }
}