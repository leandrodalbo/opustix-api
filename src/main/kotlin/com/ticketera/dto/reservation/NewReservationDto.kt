package com.ticketera.dto.reservation

import com.ticketera.model.Event
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import com.ticketera.model.Purchase
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import com.ticketera.model.TicketType
import java.time.Instant
import java.util.UUID

class NewReservationDto(
    val eventId: UUID,
    val ticketTypeId: UUID,
    val sectorId: UUID? = null,
    val seatId: UUID? = null
) {
    companion object {
        fun newReservation(purchase: Purchase, event: Event, ticketType: TicketType, sector: EventSector?, seat: EventSeat?): Reservation {
            return Reservation(
                id = UUID.randomUUID(),
                purchase = purchase,
                event = event,
                ticketType = ticketType,
                sector = sector,
                seat = seat,
                price = ticketType.price + (sector?.priceAddition ?: 0.0) + (seat?.priceAddition ?: 0.0),
                status = ReservationStatus.PENDING,
                createdAt = Instant.now().toEpochMilli()
            )
        }

        fun fromEntity(reservation: Reservation): NewReservationDto {
            return NewReservationDto(
                eventId = reservation.event.id,
                ticketTypeId = reservation.ticketType.id,
                sectorId = reservation.sector?.id,
                seatId = reservation.seat?.id
            )
        }
    }
}