package com.dhkim.springmodulith.stock

import com.dhkim.springmodulith.order.OrderCreateEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener
import java.time.LocalDateTime

@Service
class StockService(
    private val stockRepository: StockRepository,
    private val publisher: ApplicationEventPublisher
) {
//    @TransactionalEventListener
    @ApplicationModuleListener
    fun handle(event: OrderCreateEvent) {
        Thread.sleep(10000)
        println("재고 처리 Thread: ${Thread.currentThread().name}")
        println("재고 수정 처리 시작: 주문 번호 ${event.orderId}, ${LocalDateTime.now()}")

        val stock = stockRepository.findByProductId(event.productId)
            ?: throw IllegalArgumentException("상품이 존재 하지 않음")

        stock.decrease(event.quantity)
        stockRepository.save(stock)

        publisher.publishEvent(StockChangeEvent(stock.productId, -event.quantity))
    }
}
