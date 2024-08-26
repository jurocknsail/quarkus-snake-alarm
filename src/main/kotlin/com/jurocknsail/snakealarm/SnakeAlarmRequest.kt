package com.jurocknsail.snakealarm

import com.fasterxml.jackson.annotation.JsonProperty

data class SnakeAlarmRequest(
	@JsonProperty("snakes") val snakes: List<SnakeItems>
)

data class SnakeItems(
	val id: String,
	val imageASCIIArtString: String
)