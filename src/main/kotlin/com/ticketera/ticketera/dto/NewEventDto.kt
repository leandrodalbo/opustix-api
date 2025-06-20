package com.ticketera.ticketera.dto

import java.util.UUID

data class NewEventDto(
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val capacity: Int,
    val venueId: UUID
)
