package com.statemachine.call

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.listener.StateMachineListenerAdapter
import org.springframework.statemachine.state.State
import kotlin.jvm.Throws

@Configuration
@EnableStateMachine
class CallStateMachineConfig: StateMachineConfigurerAdapter<CallState, CallEvent>() {

    private val log = LoggerFactory.getLogger(CallStateMachineConfig::class.java)

    @Throws(Exception::class)
    override fun configure(config: StateMachineConfigurationConfigurer<CallState, CallEvent>) {
        config
            .withConfiguration()
            .autoStartup(true)
            .listener(object: StateMachineListenerAdapter<CallState, CallEvent>() {
                override fun stateChanged(from: State<CallState, CallEvent>?, to: State<CallState, CallEvent>?) {
                    val fromState = from?.id?.name ?: "NONE"
                    val toState = to?.id?.name ?: "NONE"
                    log.info("State changed from $fromState to $toState")
                }
            }
        )
    }

    @Throws(Exception::class)
    override fun configure(states: StateMachineStateConfigurer<CallState, CallEvent>) {
        states
            .withStates()
            .initial(CallState.WAIT)
            .state(CallState.REQUESTED)
            .state(CallState.BROADCASTING)
            .state(CallState.BOARDING)
            .end(CallState.DONE)
    }

    @Throws(Exception::class)
    override fun configure(transitions: StateMachineTransitionConfigurer<CallState, CallEvent>) {
        transitions
            .withExternal()
                .source(CallState.WAIT).target(CallState.REQUESTED).event(CallEvent.REQUEST)
            .and()
            .withExternal()
                .source(CallState.REQUESTED).target(CallState.BROADCASTING).event(CallEvent.BROADCAST)
            .and()
            .withExternal()
                .source(CallState.BROADCASTING).target(CallState.BOARDING).event(CallEvent.BOARD)
            .and()
            .withExternal()
                .source(CallState.BOARDING).target(CallState.DONE).event(CallEvent.DONE)

    }
}