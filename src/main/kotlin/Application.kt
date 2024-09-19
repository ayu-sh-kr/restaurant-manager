package org.example

import org.example.order.application.OrderAddItemUseCase
import org.example.order.application.OrderCreateUseCase
import org.example.order.application.OrderFetchAllUseCase
import org.example.order.application.OrderFetchSingleUseCase
import org.example.order.application.OrderItemRemoveUseCase
import org.example.order.domain.OrderRepository

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Repository()

@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS])
annotation class UseCase()

fun bootstrapApplication(vararg packages: String): Map<String, Any> {
    val initializedObjects = mutableMapOf<String, Any>()

    packages.forEach { pkg ->

    }

    return initializedObjects
}

data class OrderDomain(
    val orderRepository: OrderRepository,
    val orderAddItemUseCase: OrderAddItemUseCase,
    val orderItemRemoveUseCase: OrderItemRemoveUseCase,
    val orderCreateUseCase: OrderCreateUseCase,
    val orderFetchSingleUseCase: OrderFetchSingleUseCase,
    val orderFetchAllUseCase: OrderFetchAllUseCase
)

fun bootstrapOrderDomain(): OrderDomain {
    val orderRepository = OrderRepository(mutableListOf());
    val orderAddItemUseCase = OrderAddItemUseCase(orderRepository)
    val orderItemRemoveUseCase = OrderItemRemoveUseCase(orderRepository)
    val orderCreateUseCase = OrderCreateUseCase(orderRepository)
    val orderFetchSingleUseCase = OrderFetchSingleUseCase(orderRepository)
    val orderFetchAllUseCase = OrderFetchAllUseCase(orderRepository)

    return OrderDomain(
        orderRepository,
        orderAddItemUseCase,
        orderItemRemoveUseCase,
        orderCreateUseCase,
        orderFetchSingleUseCase,
        orderFetchAllUseCase
    )
}