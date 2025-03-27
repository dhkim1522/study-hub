package com.dhkim.springmodulith.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {
    fun findByProductId(productId: String): Order?
}