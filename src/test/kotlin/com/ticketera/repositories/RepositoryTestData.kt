package com.ticketera.repositories

import com.ticketera.model.Event
import com.ticketera.model.TicketType
import com.ticketera.model.Venue
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

abstract class RepositoryTestData {
    protected val venueId = UUID.randomUUID()
    protected val eventId = UUID.randomUUID()
    protected val ticketTypeId = UUID.randomUUID()

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
}