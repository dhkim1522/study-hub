package com.statemachine.call

enum class CallState {
    WAIT,             // 배차 대기
    REQUESTED,        // 배차 요청
    BROADCASTING,     // 배차 중
    BOARDING,         // 승객 탑승
    DONE              // 주행 완료
}