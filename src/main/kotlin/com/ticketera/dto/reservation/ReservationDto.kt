package com.ticketera.dto.reservation

import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import java.util.UUID

data class ReservationDto(
    val id: UUID,
    val eventId: UUID,
    val ticketTypeId: UUID,
    val ticketTypeName: String,
    val ticketTypePrice: Double,
    val ticketTypeCurrency: String,
    val price: Double,
    val status: ReservationStatus,
    val sectorId: UUID? = null,
    val sectorName: String? = null,
    val seatId: UUID? = null,
    val seatLabel: String? = null,
) {
    companion object {
        fun fromEntity(reservation: Reservation): ReservationDto =
            ReservationDto(
                reservation.id,
                reservation.event.id,
                reservation.ticketType.id,
                reservation.ticketType.name,
                reservation.ticketType.price,
                reservation.ticketType.currency,
                reservation.price,
                reservation.status,
                reservation.sector?.id,
                reservation.sector?.name,
                reservation.seat?.id,
                reservation.seat?.label
            )
    }
}