package com.ticketera.data

import com.ticketera.dto.purchase.PurchaseDto
import com.ticketera.dto.reservation.NewReservationDto
import com.ticketera.dto.reservation.ReservationDto
import com.ticketera.model.PaymentStatus
import com.ticketera.model.Purchase
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

class PurchaseReservationData {
    companion object {

        val purchase = Purchase(
            UUID.randomUUID(),
            "user data",
            100.0,
            PaymentStatus.INITIATED,
            Instant.now().plus(10, ChronoUnit.MINUTES).toEpochMilli(),
            Instant.now().toEpochMilli(),
        )

        val reservation = Reservation(
            UUID.randomUUID(),
            purchase,
            EventData.event,
            TicketTypeData.ticketType,
            EventSectorData.eventSector,
            EventSeatData.eventSeat,
            100.0,
            ReservationStatus.PENDING,
            Instant.now().toEpochMilli()
        )


        val newReservationDto = NewReservationDto(
            EventData.event.id,
            TicketTypeData.ticketType.id,
            null,
            null
        )


        val reservationDto = ReservationDto(
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
            reservation.seat?.label,
        )

        val purchaseDto = PurchaseDto(
            purchase.id,
            purchase.userInfo,
            purchase.totalPrice,
            purchase.paymentStatus,
            purchase.expiresAt,
            listOf(reservationDto)
        )
    }
}