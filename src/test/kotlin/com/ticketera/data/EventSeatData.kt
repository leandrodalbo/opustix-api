package com.ticketera.data

import com.ticketera.dto.eventSeats.EventSeatDto
import com.ticketera.dto.eventSeats.NewEventSeatsDto
import com.ticketera.model.EventSeat
import java.time.Instant
import java.util.UUID

class EventSeatData {
    companion object {
        val eventSeat = EventSeat(
            UUID.randomUUID(),
            "Testing Seat",
            "10",
            Instant.now().toEpochMilli(),
            EventSectorData.eventSector
        )

        val eventSeatDto = EventSeatDto.Companion.fromEntity(eventSeat)

        val newEventSeatsDto = NewEventSeatsDto(
            1,
            5,
            "row-0",
            EventSectorData.eventSector.id
        )
    }
}