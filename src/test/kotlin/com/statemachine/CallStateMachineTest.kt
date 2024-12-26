package com.statemachine

import com.statemachine.call.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.statemachine.StateMachine
import kotlin.test.Test

@SpringBootTest
class CallStateMachineTest {
    @Autowired
    lateinit var callService: CallService

    @Autowired
    lateinit var callRepository: CallRepository

    @Autowired
    lateinit var stateMachine: StateMachine<CallState, CallEvent>

    lateinit var call: Call

    @BeforeEach
    fun setUp() {
        // 데이터베이스에 테스트용 Call 엔티티를 저장
        call = Call(state = CallState.WAIT)
        callRepository.save(call)
    }

    @AfterEach
    fun tearDown() {
        // 데이터베이스 정리
        callRepository.deleteAll()
    }

    @Test
    fun `should update state when event is successfully sent`() {
        // Given
        val callId = call.id ?: throw IllegalArgumentException("Call id is null")
        val event = CallEvent.REQUEST

        // When
        val newState = callService.processEvent(callId, event)

        // Then
        // 상태가 변경되었음을 검증
        assertEquals(CallState.REQUESTED, newState)

        // 데이터베이스에서 상태가 올바르게 업데이트 되었는지 확인
        val updatedCall = callRepository.findById(callId).orElseThrow {
            throw IllegalArgumentException("Call not found")
        }
        assertEquals(CallState.REQUESTED, updatedCall.state)
    }

    @Test
    fun `should not update state when event is not sent successfully`() {
        // Given
        val callId = call.id ?: throw IllegalArgumentException("Call id is null")
        val event = CallEvent.BROADCAST

        // 상태 변경이 실패한 경우
        `when`(stateMachine.sendEvent(event)).thenReturn(false)

        // When
        val newState = callService.processEvent(callId, event)

        // Then
        // 상태가 변경되지 않았음
        assertEquals(CallState.WAIT, newState)

        // 데이터베이스에서 상태가 변경되지 않았는지 확인
        val updatedCall = callRepository.findById(callId).orElseThrow {
            throw IllegalArgumentException("Call not found")
        }
        assertEquals(CallState.WAIT, updatedCall.state)
    }

    @Test
    fun `상태 변경 테스트`() {
        // given
        val callId = call.id ?: throw IllegalArgumentException("Call id is null")
        val event = CallEvent.BROADCAST

        val call = callRepository.findById(callId).orElseThrow {
            throw IllegalArgumentException("Call not found")
        }

        println(" call state: ${call.state}")

        // when
        val newState = callService.processEvent(callId, event)

        // then
        println(" call state: ${call.state}")
        println(" statemachine call state: ${stateMachine.state.id}")
    }

}