package com.jurocknsail.snakealarm

import jakarta.enterprise.context.ApplicationScoped
import kotlinx.coroutines.future.await
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import java.util.logging.Level
import java.util.logging.Logger

@ApplicationScoped
class SnakeAlarmRepository(
    @Channel("snake-alarms-out") private val snakeAlarmEmitter: Emitter<SnakeAlarm>,
    private val logger: Logger
) {
    suspend fun alarm(items: List<SnakeItems>): Boolean {
        val snakeAlarm = SnakeAlarm(
            snakes = items,
            snakeVerification = "Snake could also be a lizard :-)",
            estimatedPushNotificationTimestamp = null
        )
        return try {
            snakeAlarmEmitter.send(snakeAlarm).await()
            true
        } catch (e: Exception) {
            logger.log(Level.SEVERE, "Error while sending snake alarm", e)
            false
        }
    }
}