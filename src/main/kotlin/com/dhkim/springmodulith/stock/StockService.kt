package com.dhkim.springmodulith.stock

import com.dhkim.springmodulith.order.OrderCreateEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockRepository: StockRepository,
    private val publisher: ApplicationEventPublisher
) {
    @EventListener
    fun handle(event: OrderCreateEvent) {
        println("재고 수정 처리 시작: 주문 번호 ${event.orderId}")

        val stock = stockRepository.findByProductId(event.productId)
            ?: throw IllegalArgumentException("상품이 존재 하지 않음")

        stock.decrease(event.quantity)
        stockRepository.save(stock)

        publisher.publishEvent(StockChangeEvent(stock.productId, -event.quantity))
    }
}
