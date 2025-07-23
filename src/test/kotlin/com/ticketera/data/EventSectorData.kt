package com.ticketera.data

import com.ticketera.dto.eventSectors.EventSectorDto
import com.ticketera.dto.eventSectors.NewEventSectorDto
import com.ticketera.dto.eventSectors.UpdateEventSectorDto
import com.ticketera.model.EventSector
import java.time.Instant
import java.util.UUID

class EventSectorData {
    companion object {
        val eventSector = EventSector(
            UUID.randomUUID(),
            "Testing Event Sector",
            "Testing Event Sector",
            Instant.now().toEpochMilli(),
            TicketTypeData.ticketType
        )

        val eventSectorDto = EventSectorDto.Companion.fromEntity(eventSector)

        val newEventSectorDto = NewEventSectorDto(
            "sector-1",
            "test-sector",
            TicketTypeData.ticketType.id
        )

        val updateEventSectorDto = UpdateEventSectorDto(
            eventSector.id,
            "sector-1-new-name",
            "updated-testing-sector",
            11.11,
            TicketTypeData.ticketType.id
        )
    }
}