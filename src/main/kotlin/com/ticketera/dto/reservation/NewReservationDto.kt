package com.ticketera.dto.reservation

import com.ticketera.aux.PendingReservation
import com.ticketera.model.Purchase
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import java.time.Instant
import java.util.UUID

data class NewReservationDto(
    val eventId: UUID,
    val ticketTypeId: UUID,
    val sectorId: UUID? = null,
    val seatId: UUID? = null
) {
    companion object {
        fun newReservation(purchase: Purchase, pendingReservation: PendingReservation): Reservation {
            return Reservation(
                id = UUID.randomUUID(),
                purchase = purchase,
                event = pendingReservation.event,
                ticketType = pendingReservation.ticketType,
                sector = pendingReservation.sector,
                seat = pendingReservation.seat,
                price = pendingReservation.price,
                status = ReservationStatus.PENDING,
                createdAt = Instant.now().toEpochMilli()
            )
        }

    }
}