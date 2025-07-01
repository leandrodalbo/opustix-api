package com.ticketera.dto.events

import com.ticketera.model.Event
import com.ticketera.model.Venue
import java.util.UUID

data class UpdateEventDto(
    val id: UUID,
    val title: String?,
    val description: String?,
    val startTime: Long?,
    val endTime: Long?,
    val capacity: Int?,
    val category: String?,
    val venueId: UUID?
) {
    companion object {
        fun fromEntity(event: Event): UpdateEventDto {
            return UpdateEventDto(
                id = event.id,
                title = event.title,
                description = event.description,
                startTime = event.startTime,
                endTime = event.endTime,
                capacity = event.capacity,
                category = event.category,
                venueId = event.venue.id
            )
        }

        fun updatedEvent(updateEventDto: UpdateEventDto, venue: Venue, event: Event): Event {
            return Event(
                id = updateEventDto.id,
                title = updateEventDto.title ?: event.title,
                description = updateEventDto.description ?: event.description,
                startTime = updateEventDto.startTime ?: event.startTime,
                endTime = updateEventDto.endTime ?: event.endTime,
                capacity = updateEventDto.capacity ?: event.capacity,
                category = updateEventDto.category ?: event.category,
                venue = venue,
                createdAt = event.createdAt
            )
        }
    }
}