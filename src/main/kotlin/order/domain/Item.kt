package org.example.order.domain

import java.util.UUID


class Item(var name: String, var price: Double, var quantity: Int) {
    val id = ItemId(UUID.randomUUID())

    fun getTotal(): Double {
        return this.price * this.quantity
    }

    override fun toString(): String {
        return "Item(name='$name', price=$price, quantity=$quantity)"
    }

}

data class ItemId(val id: UUID) {
    override fun toString(): String {
        return "ItemId(id=$id)"
    }
}