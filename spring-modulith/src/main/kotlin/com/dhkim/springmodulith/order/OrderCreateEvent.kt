package com.dhkim.springmodulith.order

data class OrderCreateEvent(
    val orderId: Long,
    val productId: String,
    val quantity: Int
)
