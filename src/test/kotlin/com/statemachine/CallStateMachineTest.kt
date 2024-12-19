package com.statemachine

import com.statemachine.call.CallEvent
import com.statemachine.call.CallService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CallStateMachineTest {

    @Autowired
    lateinit var callService: CallService

    @Test
    fun `test state transitions`() {
        var currentState = callService.processEvent(CallEvent.REQUEST)
        println("currentState: $currentState")

        currentState = callService.processEvent(CallEvent.BROADCAST)
        println("currentState: $currentState")

        currentState = callService.processEvent(CallEvent.BOARD)
        println("currentState: $currentState")

        currentState = callService.processEvent(CallEvent.DONE)
        println("currentState: $currentState")
    }

}
