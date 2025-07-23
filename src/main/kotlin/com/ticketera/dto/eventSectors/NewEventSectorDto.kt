package com.ticketera.dto.eventSectors

import com.ticketera.model.EventSector
import com.ticketera.model.TicketType
import java.time.Instant
import java.util.UUID

data class NewEventSectorDto(
    val name: String,
    val description: String? = null,
    val ticketTypeId: UUID
) {
    companion object {

        fun newEventSector(newEventSectorDto: NewEventSectorDto, ticketType: TicketType): EventSector {
            return EventSector(
                id = UUID.randomUUID(),
                newEventSectorDto.name,
                newEventSectorDto.description,
                Instant.now().toEpochMilli(),
                ticketType
            )
        }
    }
}