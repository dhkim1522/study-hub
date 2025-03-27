package com.dhkim.springmodulith.delivery

import com.dhkim.springmodulith.order.OrderCreateEvent
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener
import java.time.LocalDateTime

@Service
class DeliveryService {
//    @TransactionalEventListener
    @ApplicationModuleListener
    fun handle(event: OrderCreateEvent) {
        Thread.sleep(10000)
        println("배송 처리 Thread: ${Thread.currentThread().name}")
        println("배송 처리 시작: 주문 번호 ${event.orderId}, ${LocalDateTime.now()}")
        // 배송 저장 생략
    }
}
