package com.ticketera.dto.eventSeats

import com.ticketera.model.Event
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import java.time.Instant
import java.util.UUID

data class NewEventSeatsDto(
    val fromNumber: Int,
    val toNumber: Int,
    val label: String,
    val seatRowInfo: String? = null,
    val priceAddition: Double? = null,
    val eventId: UUID,
    val sectorId: UUID?
) {
    companion object {

        fun newEventSeats(dto: NewEventSeatsDto, event: Event, eventSector: EventSector?): List<EventSeat> {
            return (dto.fromNumber..dto.toNumber).map { number ->
                EventSeat(
                    id = UUID.randomUUID(),
                    event = event,
                    sector = eventSector,
                    label = dto.label,
                    seatRowInfo = dto.seatRowInfo,
                    seatNumber = number.toString(),
                    priceAddition = dto.priceAddition,
                    createdAt = Instant.now().toEpochMilli()
                )
            }
        }
    }
}