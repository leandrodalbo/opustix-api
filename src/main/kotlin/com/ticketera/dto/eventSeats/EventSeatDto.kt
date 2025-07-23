package com.ticketera.dto.eventSeats

import com.ticketera.model.EventSeat
import java.util.UUID

data class EventSeatDto(
    val id: UUID,
    val label: String,
    val seatNumber: String?,
    val sectorId: UUID?
) {
    companion object {
        fun fromEntity(seat: EventSeat): EventSeatDto {
            return EventSeatDto(
                id = seat.id,
                label = seat.label,
                seatNumber = seat.seatNumber,
                sectorId = seat.sector.id
            )
        }
    }
}
