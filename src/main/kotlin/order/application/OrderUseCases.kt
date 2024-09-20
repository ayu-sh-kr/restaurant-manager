package org.example.order.application

import org.example.UseCase
import org.example.customer.domain.CustomerId
import org.example.order.domain.Item
import org.example.order.domain.ItemId
import org.example.order.domain.Order
import org.example.order.domain.OrderId
import org.example.order.domain.OrderRepository

@UseCase
class OrderItemRemoveUseCase(private val orderRepository: OrderRepository) {


    fun execute(orderId: OrderId, itemId: ItemId) {
        val order = orderRepository.findById(orderId);
        require(order != null, ({"Order was not found for given id: $orderId"}))
        order.removeItem(itemId)
        orderRepository.update(order, orderId)
    }
}

@UseCase
class OrderCreateUseCase(private val orderRepository: OrderRepository) {

    fun execute(customerId: CustomerId, items: MutableList<Item>): Order {
        val order = Order(
            customerId = customerId,
            items = items
        )

        return orderRepository.save(order)
    }

}

@UseCase
class OrderAddItemUseCase(private val orderRepository: OrderRepository) {

    fun execute(item: Item, orderId: OrderId) {
        val order = orderRepository.findById(orderId)
        require(order != null, ({"Order was not found for given id: $orderId"}))
        order.addItem(item)
        orderRepository.update(order, orderId)
    }
}

@UseCase
class OrderFetchSingleUseCase(private val orderRepository: OrderRepository) {

    fun execute(orderId: OrderId): Order? {
        return orderRepository.findById(orderId)
    }
}

@UseCase
class OrderFetchAllUseCase(private val orderRepository: OrderRepository) {

    fun execute(): MutableList<Order> {
        return orderRepository.findAll()
    }
}