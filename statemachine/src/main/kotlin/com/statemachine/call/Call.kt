package com.statemachine.call

import jakarta.persistence.*

@Entity
@Table
class Call(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var state: CallState = CallState.WAIT,
)