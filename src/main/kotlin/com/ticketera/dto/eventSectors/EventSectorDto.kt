package com.ticketera.dto.eventSectors

import com.ticketera.model.EventSector
import com.ticketera.model.TicketType
import java.util.UUID

data class EventSectorDto(
    val id: UUID,
    val name: String,
    val description: String?,
    val ticketTypeId: UUID
) {
    companion object {
        fun fromEntities(sector: EventSector, ticketType: TicketType) = EventSectorDto(
            id = sector.id,
            name = sector.name,
            ticketTypeId = ticketType.id,
            description = sector.description
        )
    }
}
