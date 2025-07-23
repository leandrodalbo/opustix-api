package com.ticketera.dto.eventSectors

import com.ticketera.model.EventSector
import com.ticketera.model.TicketType
import java.util.UUID

data class UpdateEventSectorDto(
    val id: UUID,
    val name: String?,
    val description: String? = null,
    val priceAddition: Double? = null,
    val ticketTypeId: UUID?
) {
    companion object {
        fun updatedEventSector(
            dto: UpdateEventSectorDto,
            eventSector: EventSector,
            ticketType: TicketType
        ): EventSector {
            return EventSector(
                eventSector.id,
                dto.name ?: eventSector.name,
                dto.description ?: eventSector.description,
                eventSector.createdAt,
                ticketType
            )
        }
    }
}