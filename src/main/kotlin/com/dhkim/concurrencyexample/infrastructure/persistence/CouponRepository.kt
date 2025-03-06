package com.dhkim.concurrencyexample.infrastructure.persistence

import com.dhkim.concurrencyexample.domain.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository: JpaRepository<Coupon, Long> {
}