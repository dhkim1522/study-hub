package com.dhkim.springmodulith.order

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue
    val id: Long? = null,
    val productId: String,
    val quantity: Int
)
