package com.statemachine.call

import org.springframework.data.jpa.repository.JpaRepository

interface CallRepository: JpaRepository<Call, Long> {
}