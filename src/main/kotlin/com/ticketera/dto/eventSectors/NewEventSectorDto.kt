package com.ticketera.dto.eventSectors

import com.ticketera.model.Event
import com.ticketera.model.EventSector
import java.time.Instant
import java.util.UUID

data class NewEventSectorDto(
    val name: String,
    val description: String? = null,
    val priceAddition: Double? = null,
    val eventId: UUID
) {
    companion object {
        fun fromEntity(eventSector: EventSector): NewEventSectorDto {
            return NewEventSectorDto(
                eventSector.name,
                eventSector.description,
                eventSector.priceAddition,
                eventSector.event.id,
            )
        }

        fun newEventSector(newEventSectorDto: NewEventSectorDto, event: Event): EventSector {
            return EventSector(
                id = UUID.randomUUID(),
                newEventSectorDto.name,
                newEventSectorDto.description,
                newEventSectorDto.priceAddition,
                Instant.now().toEpochMilli(),
                event
            )
        }
    }
}