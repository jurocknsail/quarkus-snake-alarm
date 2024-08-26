package com.jurocknsail.snakealarm

data class SnakeAlarm(
    val snakes: List<SnakeItems>,
    val snakeVerification: String?,
    val estimatedPushNotificationTimestamp: Long?
)