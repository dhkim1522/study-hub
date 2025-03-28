package com.statemachine.call

import org.slf4j.LoggerFactory
import org.springframework.statemachine.StateMachine
import org.springframework.stereotype.Service

@Service
class CallService(
    private val stateMachine: StateMachine<CallState, CallEvent>,
    private val callRepository: CallRepository
) {
    private val log = LoggerFactory.getLogger(CallService::class.java)

    fun processEvent(callId: Long, event: CallEvent): CallState {
        val call = callRepository.findById(callId).orElseThrow {
            throw IllegalArgumentException("Call not found")
        }

        if(stateMachine.sendEvent(event)) {
            // 상태가 성공적으로 변경되었을 때만 DB에 반영
            call.state = stateMachine.state.id
            callRepository.save(call)
            log.info("Call state changed to ${stateMachine.state.id}")
        } else {
            // 이벤트 처리 실패 시 상태 변경 안 함
            log.warn("Event processing failed. State remains ${call.state}")
        }
        return call.state
    }
}
