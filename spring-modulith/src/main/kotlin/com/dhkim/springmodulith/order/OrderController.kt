package com.dhkim.springmodulith.order

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {

    data class CreateOrderRequest(val productId: String, val quantity: Int)

    @PostMapping
    fun createOrder(@RequestBody request: CreateOrderRequest): Order {
        return orderService.createOrder(request.productId, request.quantity)
    }
}