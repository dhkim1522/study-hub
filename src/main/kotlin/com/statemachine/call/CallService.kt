package com.statemachine.call

import org.springframework.statemachine.StateMachine
import org.springframework.stereotype.Service

@Service
class CallService(
    private val stateMachine: StateMachine<CallState, CallEvent>,
) {

    fun processEvent(event: CallEvent): CallState {
        stateMachine.sendEvent(event)
        return stateMachine.state.id
    }
}
