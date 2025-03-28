package com.dhkim.springmodulith.order

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val publisher: ApplicationEventPublisher
) {
    @Transactional
    fun createOrder(productId: String, quantity: Int): Order {
        val savedOrder = orderRepository.save(
            Order(
                productId = productId,
                quantity = quantity
            )
        )
        publisher.publishEvent(OrderCreateEvent(savedOrder.id!!, productId, quantity))

        return savedOrder
    }
}
