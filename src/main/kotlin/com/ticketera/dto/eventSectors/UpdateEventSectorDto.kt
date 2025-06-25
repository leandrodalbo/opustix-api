package com.ticketera.dto.eventSectors

import com.ticketera.model.Event
import com.ticketera.model.EventSector
import java.util.UUID

data class UpdateEventSectorDto(
    val id: UUID,
    val name: String?,
    val description: String? = null,
    val priceAddition: Double? = null,
    val eventId: UUID?
) {
    companion object {
        fun fromEntity(eventSector: EventSector): UpdateEventSectorDto {
            return UpdateEventSectorDto(
                eventSector.id,
                eventSector.name,
                eventSector.description,
                eventSector.priceAddition,
                eventSector.event.id
            )
        }

        fun updatedEventSector(dto: UpdateEventSectorDto, eventSector: EventSector, event: Event): EventSector {
            return EventSector(
                eventSector.id,
                dto.name ?: eventSector.name,
                dto.description ?: eventSector.description,
                dto.priceAddition ?: eventSector.priceAddition,
                eventSector.createdAt,
                event
            )
        }
    }
}