package com.dhkim.springmodulith.stock

data class StockChangeEvent(
    val productId: String,
    val changedAmount: Int
)
