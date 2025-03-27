package com.dhkim.springmodulith.delivery

import com.dhkim.springmodulith.order.OrderCreateEvent
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Service

@Service
class DeliveryService {
    @ApplicationModuleListener
    fun handle(event: OrderCreateEvent) {
        println("배송 처리 시작: 주문 번호 ${event.orderId}")
        // 배송 저장 생략
    }
}
