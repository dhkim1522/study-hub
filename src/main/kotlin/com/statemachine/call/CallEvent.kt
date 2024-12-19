package com.statemachine.call

enum class CallEvent {
    REQUEST,          // 배차 요청
    BROADCAST,        // 배차 실행
    BOARD,            // 승객 탑승
    DONE              // 주행 완료
}