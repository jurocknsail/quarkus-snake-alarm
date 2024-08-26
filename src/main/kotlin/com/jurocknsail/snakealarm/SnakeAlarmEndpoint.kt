package com.jurocknsail.snakealarm


import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response


@Path("snake-alarm")
class SnakeAlarmEndpoint(
	private val snakeAlarmRepository: SnakeAlarmRepository
) {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	suspend fun snakeAlarm(request: SnakeAlarmRequest): Response {
		return when (snakeAlarmRepository.alarm(request.snakes)) {
			true  -> Response.accepted().build()
			false -> Response.serverError().build()
		}
	}
}