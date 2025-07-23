package com.ticketera.dto.eventSeats

import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import java.time.Instant
import java.util.UUID

data class NewEventSeatsDto(
    val fromNumber: Int,
    val toNumber: Int,
    val label: String,
    val sectorId: UUID
) {
    companion object {

        fun newEventSeats(dto: NewEventSeatsDto, eventSector: EventSector): List<EventSeat> {
            return (dto.fromNumber..dto.toNumber).map { number ->
                EventSeat(
                    UUID.randomUUID(),
                    dto.label,
                    number.toString(),
                    Instant.now().toEpochMilli(),
                    eventSector
                )
            }
        }
    }
}