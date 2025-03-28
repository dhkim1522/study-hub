package com.statemachine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StatemachineApplication

fun main(args: Array<String>) {
    runApplication<StatemachineApplication>(*args)
}
