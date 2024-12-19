package com.statemachine.call

import java.util.*

class Call(
    val id: Long = UUID.randomUUID().mostSignificantBits,
    val state: CallState,
)