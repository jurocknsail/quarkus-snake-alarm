package com.jurocknsail.snakealarm

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.reactive.messaging.Incoming
import java.util.logging.Logger

@ApplicationScoped
class SnakeAlarmProcessor(
    private val pushNotificationService: PushNotificationService,
    private val lizardCheck: LizardCheck,
    private val logger: Logger
) {
    @Incoming("snake-alarms-in")
    suspend fun processSnakeAlarm(snakeAlarm: SnakeAlarm) {
        val isVerifiedSnake = lizardCheck.checkSnakeAlarmValid(snakeAlarm)
        val verificationResultText = if (isVerifiedSnake) "Danger - Successfully verified a snake" else "False positive - Successfully verified a lizard"

        logger.info { "Received an alarm for snake alarm fulfillment: ${snakeAlarm.snakes}, $verificationResultText" }
        if(isVerifiedSnake) pushNotificationService.announcePrimaryPushNotificationService()
    }
}