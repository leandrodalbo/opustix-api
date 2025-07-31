package com.ticketera.data

import com.ticketera.dto.eventSectors.EventSectorDto
import com.ticketera.dto.eventSectors.NewEventSectorDto
import com.ticketera.dto.eventSectors.UpdateEventSectorDto
import com.ticketera.model.EventSector
import java.time.Instant
import java.util.UUID

class EventSectorData {

    companion object {

        val eventSector: EventSector by lazy {
            EventSector(
                UUID.randomUUID(),
                "Testing Event Sector",
                "Testing Event Sector",
                Instant.now().toEpochMilli(),
                TicketTypeData.ticketType,
                emptySet()
            )
        }

        val eventSectorDto = EventSectorDto(
            eventSector.id,
            eventSector.name,
            eventSector.description,
            eventSector.ticketType.id
        )

        val newEventSectorDto = NewEventSectorDto(
            "sector-1",
            "test-sector",
            eventSector.ticketType.id
        )

        val updateEventSectorDto = UpdateEventSectorDto(
            eventSector.id,
            "sector-1-new-name",
            "updated-testing-sector",
            11.11,
            eventSector.ticketType.id
        )
    }
}