package com.ticketera.dto.eventSeats

import com.ticketera.dto.eventSectors.EventSectorDto
import com.ticketera.model.EventSeat
import java.util.UUID

data class EventSeatDto(
    val id: UUID,
    val label: String,
    val seatRowInfo: String?,
    val seatNumber: String?,
    val priceAddition: Double?,
    val sector: EventSectorDto?
) {
    companion object {
        fun fromEntity(seat: EventSeat): EventSeatDto {
            return EventSeatDto(
                id = seat.id,
                label = seat.label,
                seatRowInfo = seat.seatRowInfo,
                seatNumber = seat.seatNumber,
                priceAddition = seat.priceAddition,
                sector = seat.sector?.let { EventSectorDto.fromEntity(it) }
            )
        }
    }
}
