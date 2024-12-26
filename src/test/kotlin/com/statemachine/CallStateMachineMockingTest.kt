package com.statemachine

import com.statemachine.call.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.state.State
import java.util.*

@ExtendWith(MockitoExtension::class)
class CallStateMachineMockingTest {
    @Mock
    lateinit var callRepository: CallRepository

    @Mock
    lateinit var stateMachine: StateMachine<CallState, CallEvent>

    @InjectMocks
    lateinit var callService: CallService

    lateinit var call: Call

    @BeforeEach
    fun setup() {
        // 테스트용 Call 객체 초기화
        call = Call(id = 1L, state = CallState.WAIT)

        // callRepository.findById()의 동작을 정의 (Optional 반환)
        `when`(callRepository.findById(1L)).thenReturn(Optional.of(call))

        // stateMachine.sendEvent()가 true를 반환하는 동작 정의
        `when`(stateMachine.sendEvent(CallEvent.REQUEST)).thenReturn(true)

        // 상태 변경을 Mocking (이벤트 처리 후 상태가 REQUESTED로 변경된다고 가정)
        val newState = mock(State::class.java) as State<CallState, CallEvent>
        `when`(newState.id).thenReturn(CallState.REQUESTED)
        `when`(stateMachine.state).thenReturn(newState)
    }

    @Test
    fun `should process event and update state successfully`() {
        // Given
        val callId = 1L
        val event = CallEvent.REQUEST

        // When
        val newState = callService.processEvent(callId, event)

        println("newState: $newState")
        println("call.id: ${call.id}")
        println("call.state: ${call.state}")

        // Then
        // 상태가 변경되었는지 확인
        assertEquals(CallState.REQUESTED, newState)

        // stateMachine.sendEvent()가 호출되었는지 검증
        verify(stateMachine).sendEvent(event)

        // callRepository.save()가 호출되었는지 검증
        verify(callRepository).save(call)

        // 상태가 DB에 반영되었는지 확인
        assertEquals(CallState.REQUESTED, call.state)
    }

    @Test
    fun `should throw exception when call not found`() {
        // Given
        val callId = 999L // 존재하지 않는 callId
        val event = CallEvent.REQUEST

        // When / Then
        assertThrows(IllegalArgumentException::class.java) {
            callService.processEvent(callId, event)
        }

        // callRepository.findById()가 호출되었는지 검증
        verify(callRepository).findById(callId)
    }

    @Test
    fun `should not update state when event is not sent successfully`() {
        // Given
        val callId = 1L
        val event = CallEvent.REQUEST

        // When
        `when`(stateMachine.sendEvent(event)).thenReturn(false)

        // Then
        val newState = callService.processEvent(callId, event)

        // 상태가 변경되지 않았으므로 원래 상태가 그대로여야 한다.
        assertEquals(CallState.WAIT, newState)

        // stateMachine.sendEvent()가 호출되었는지 검증
        verify(stateMachine).sendEvent(event)

        // callRepository.save()가 호출되지 않았으므로 검증되지 않음
        verify(callRepository, never()).save(call)
    }
}
