package com.dhkim.concurrencyexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConcurrencyExampleApplication

fun main(args: Array<String>) {
    runApplication<ConcurrencyExampleApplication>(*args)
}
