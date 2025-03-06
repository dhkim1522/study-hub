package com.dhkim.concurrencyexample

import com.dhkim.concurrencyexample.application.CouponService
import com.dhkim.concurrencyexample.domain.Coupon
import com.dhkim.concurrencyexample.infrastructure.persistence.CouponRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import kotlin.test.Test

@SpringBootTest
class CouponLockTest {
    @Autowired lateinit var couponService: CouponService
    @Autowired lateinit var couponRepository: CouponRepository

    private lateinit var coupon: Coupon

    @BeforeEach
    fun setUp() {
        coupon = Coupon(name = "무료사용권", availableStock = 100L)
        println("set coupon : $coupon")
        couponRepository.save(coupon)
    }

    @Test
    fun `쿠폰 차감 테스트`() {
        println("get Coupon1 : ${coupon}")

        couponService.couponDecrease(
            lockName = coupon.name,
            couponId = coupon.id!!
        )

        println("get Coupon2 : ${coupon}")
    }

    /**
     * Feature: 쿠폰 차감 동시성 테스트
     * Background
     *     Given "무료이용권"라는 이름의 쿠폰 100장이 등록되어 있음
     *
     * Scenario: 100장의 쿠폰을 100명의 사용자가 동시에 접근해 발급 요청함
     *           Lock의 이름은 쿠폰명으로 설정함
     *
     * Then 사용자들의 요청만큼 정확히 쿠폰의 개수가 차감되어야 함
     */
    @Test
    fun `쿠폰차감 분산락 적용 동시성 100명 테스트`() {
        val numberOfThreads = 1
        val executorService = Executors.newFixedThreadPool(numberOfThreads)
        val latch = CountDownLatch(numberOfThreads)

        println("id: ${coupon.id}")
        println("name: ${coupon.name}")

        repeat(numberOfThreads) {
            executorService.submit {
                try {
                    // 분산락 적용 메서드 호출 (락의 key는 쿠폰의 name으로 설정)
                    couponService.couponDecrease(
                        lockName = coupon.name,
                        couponId = coupon.id!!
                    )
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        val persistCoupon = couponRepository.findById(coupon.id!!)
            .orElseThrow { IllegalArgumentException() }

        assertEquals(0L, persistCoupon.availableStock)
        println("잔여 쿠폰 개수 = ${persistCoupon.availableStock}")
    }
}