package com.dhkim.concurrencyexample.infrastructure.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class DistributedLockAspect(
    private val redissonClient: RedissonClient,
    private val aopForTransaction: AopForTransaction
) {
    companion object {
        private const val REDISSON_LOCK_PREFIX = "LOCK:"
        private val log = LoggerFactory.getLogger(DistributedLockAspect::class.java)
    }

    @Around("@annotation(com.dhkim.concurrencyexample.infrastructure.aop.DistributedLock)")
    @Throws(Throwable::class)
    fun lock(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature as MethodSignature
        val method: Method = signature.method
        val distributedLock = method.getAnnotation(DistributedLock::class.java)

        val key = REDISSON_LOCK_PREFIX + CustomSpringELParser.getDynamicValue(
            signature.parameterNames, joinPoint.args, distributedLock.key
        )
        val rLock: RLock = redissonClient.getLock(key)  // (1)

        return try {
            val available = rLock.tryLock(distributedLock.waitTime, distributedLock.leaseTime, distributedLock.timeUnit)  // (2)
            if (!available) {
                return false
            }
            aopForTransaction.proceed(joinPoint)  // (3)
        } catch (e: InterruptedException) {
            throw InterruptedException()
        } finally {
            try {
                rLock.unlock()  // (4)
            } catch (e: IllegalMonitorStateException) {
                log.info("Redisson Lock Already Unlocked. serviceName={}, key={}", method.name, key)
            }
        }
    }
}
