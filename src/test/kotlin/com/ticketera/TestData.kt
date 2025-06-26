package com.ticketera

import com.ticketera.dto.eventSectors.NewEventSectorDto
import com.ticketera.dto.eventSectors.UpdateEventSectorDto
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
import com.ticketera.dto.ticketTypes.NewTicketTypeDto
import com.ticketera.dto.ticketTypes.UpdateTicketTypeDto
import com.ticketera.dto.venues.NewVenueDto
import com.ticketera.dto.venues.UpdateVenueDto
import com.ticketera.model.Event
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import com.ticketera.model.TicketType
import com.ticketera.model.Venue
import org.springframework.http.HttpHeaders
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

abstract class TestData {
    protected val venueId = UUID.randomUUID()
    protected val eventId = UUID.randomUUID()
    protected val ticketTypeId = UUID.randomUUID()
    protected val eventSectorId = UUID.randomUUID()
    protected val eventSeatId = UUID.randomUUID()

    protected val headersMap = mapOf(
        "x-Roles" to "USER,X-USER-ROLE,ADMIN",
        "X-name" to "any-name",
        "x-email" to "any@mail.com",
    )

    val httpHeaders = HttpHeaders().apply {
        add("X-Roles", "ADMIN,USER")
        add("Authorization", "Bearer token")
    }

    protected val venue = Venue(
        venueId,
        "venue-0",
        address = "Road x at 1324",
        Instant.now().toEpochMilli()
    )

    protected val event = Event(
        eventId,
        "event-x",
        "event-x",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        1000,
        venue,
        Instant.now().toEpochMilli()
    )

    protected val ticketType = TicketType(
        ticketTypeId,
        "GOLDEN",
        BigDecimal(132.44),
        "ARS",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        500,
        "Golden Ticket",
        Instant.now().toEpochMilli(),
        event
    )

    protected val eventSector = EventSector(
        eventSectorId,
        "Testing Event Sector",
        "Testing Event Sector",
        10.0,
        Instant.now().toEpochMilli(),
        event
    )

    protected val eventSeat = EventSeat(
        eventSeatId,
        "Testing Seat",
        "Seat on Row X",
        "10",
        0.0,
        Instant.now().toEpochMilli(),
        event,
        eventSector
    )


    protected val newVenueDto = NewVenueDto(
        "new-venue",
        "road x"
    )

    protected val updateVenueDto = UpdateVenueDto(
        venue.id,
        "new-venue-name",
        "new-venue-address"
    )

    protected val newEventDto = NewEventDto(
        "event-x",
        "event-x",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        1000,
        venue.id
    )

    protected val updateEventDto = UpdateEventDto(
        id = UUID.randomUUID(),
        "event-new-title",
        "event-updated",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        1000,
        venue.id
    )

    protected val newTicketTypeDto = NewTicketTypeDto(
        "GOLDEN",
        BigDecimal(150.0),
        "ARS",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        500,
        "golden-ticket",
        event.id
    )

    protected val updateTicketTypeDto = UpdateTicketTypeDto(
        ticketTypeId,
        "GOLDEN",
        BigDecimal(150.0),
        "ARS",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        500,
        "golden-ticket",
        event.id
    )

    protected val newEventSectorDto = NewEventSectorDto(
        "sector-1",
        "test-sector",
        11.11,
        eventId
    )

    protected val updateEventSectorDto = UpdateEventSectorDto(
        eventSectorId,
        "sector-1-new-name",
        "updated-testing-sector",
        11.11,
        eventId
    )
}