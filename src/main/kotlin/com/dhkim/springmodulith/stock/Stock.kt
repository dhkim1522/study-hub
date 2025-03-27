package com.dhkim.springmodulith.stock

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Stock(
    @Id
    val productId: String,
    var quantity: Int
) {
    fun decrease(amount: Int) {
        if (quantity < amount) throw IllegalStateException("재고 부족")
        quantity -= amount
    }
}
