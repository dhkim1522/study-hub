package com.dhkim.concurrencyexample.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    var availableStock: Long
) {
    fun decrease() {
        validateStockCount()
        availableStock -= 1
    }

    private fun validateStockCount() {
        if (availableStock < 1) {
            throw IllegalArgumentException("쿠폰 재고가 부족합니다.")
        }
    }

    override fun toString(): String {
        return "Coupon(id=$id, name='$name', availableStock=$availableStock)"
    }
}