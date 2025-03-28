package com.dhkim.concurrencyexample.application

import com.dhkim.concurrencyexample.infrastructure.aop.DistributedLock
import com.dhkim.concurrencyexample.infrastructure.persistence.CouponRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CouponService(
    private val couponRepository: CouponRepository
) {
    @Transactional
    fun couponDecrease(couponId: Long) {
        val coupon = couponRepository.findById(couponId)
            .orElseThrow { IllegalArgumentException("존재하지 않는 쿠폰입니다.") }

        coupon.decrease()
    }

    @DistributedLock(key = "#lockName")
    fun couponDecrease(lockName: String, couponId: Long) {
        val coupon = couponRepository.findById(couponId)
            .orElseThrow { IllegalArgumentException("존재하지 않는 쿠폰입니다.") }

        coupon.decrease()
    }
}