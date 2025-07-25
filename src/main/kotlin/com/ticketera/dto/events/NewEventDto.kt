package com.ticketera.dto.events

import com.ticketera.model.Event
import com.ticketera.model.Venue
import java.time.Instant
import java.util.UUID
import kotlin.String

data class NewEventDto(
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val capacity: Int,
    val category: String,
    val venueId: UUID
) {
    companion object {

        fun newEvent(newEventDto: NewEventDto, venue: Venue): Event {
            return Event(
                id = UUID.randomUUID(),
                title = newEventDto.title,
                description = newEventDto.description,
                startTime = newEventDto.startTime,
                endTime = newEventDto.endTime,
                capacity = newEventDto.capacity,
                category = newEventDto.category,
                venue = venue,
                createdAt = Instant.now().toEpochMilli()
            )
        }
    }
}