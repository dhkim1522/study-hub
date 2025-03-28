package com.dhkim.springmodulith.stock

import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, String> {
    fun findByProductId(productId: String): Stock?
}
