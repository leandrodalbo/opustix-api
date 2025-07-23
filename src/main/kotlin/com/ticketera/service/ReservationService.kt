package com.ticketera.service

import com.ticketera.aux.PendingReservation
import com.ticketera.dto.purchase.PurchaseDto
import com.ticketera.dto.reservation.NewReservationDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Event
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import com.ticketera.model.PaymentStatus
import com.ticketera.model.Purchase
import com.ticketera.model.TicketType
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.EventSeatRepository
import com.ticketera.repositories.EventSectorRepository
import com.ticketera.repositories.PurchaseRepository
import com.ticketera.repositories.ReservationRepository
import com.ticketera.repositories.TicketTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val purchaseRepository: PurchaseRepository,
    private val eventRepository: EventRepository,
    private val ticketTypeRepository: TicketTypeRepository,
    private val eventSectorRepository: EventSectorRepository,
    private val eventSeatRepository: EventSeatRepository

) {

    @Transactional
    fun newReservations(reservationsDto: List<NewReservationDto>, userMail: String): PurchaseDto {
        if (reservationsDto.isEmpty()) {
            throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        }

        val toBeSaved = pendingReservations(reservationsDto)

        validateReservations(toBeSaved)

        val purchase = purchaseRepository.save(
            Purchase(
                UUID.randomUUID(),
                userMail,
                toBeSaved.sumOf { it.price },
                PaymentStatus.INITIATED,
                Instant.now().plus(30, ChronoUnit.MINUTES).toEpochMilli(),
                Instant.now().toEpochMilli(),
                emptyList()
            )
        )

        val savedReservations =
            reservationRepository.saveAll(toBeSaved.map { NewReservationDto.newReservation(purchase, it) })
                .toList()

        return PurchaseDto.fromEntities(purchase, savedReservations)
    }


    private fun pendingReservations(reservationsDto: List<NewReservationDto>) =
        reservationsDto.map {
            val event: Event = eventRepository.findById(it.eventId).orElseThrow {
                TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
            }

            val ticketType: TicketType = ticketTypeRepository.findById(it.ticketTypeId).orElseThrow {
                TicketeraException(ErrorMessage.TICKET_TYPE_NOT_FOUND)

            }

            val sector: EventSector? = it.sectorId?.let { sectorId ->
                eventSectorRepository.findById(sectorId).orElseThrow {
                    TicketeraException(ErrorMessage.EVENT_SECTOR_NOT_FOUND)

                }
            }

            val seat: EventSeat? = it.seatId?.let { seatId ->
                eventSeatRepository.findById(seatId).orElseThrow {
                    TicketeraException(ErrorMessage.EVENT_SEAT_NOT_FOUND)

                }
            }

            PendingReservation(
                event,
                ticketType,
                sector,
                seat,
                ticketType.price
            )
        }

    private fun validateReservations(pendingReservations: List<PendingReservation>) {
        pendingReservations.forEach { reservation ->
            validateTicketType(reservation.ticketType)

            if (reservation.sector != null && reservation.seat != null) {
                validateSectorAndSeat(reservation.sector.id, reservation.seat.id)
            }
        }

    }

    private fun validateTicketType(ticketType: TicketType) {
        if (reservationRepository.countActiveReservationsByTicketTypeId(ticketType.id) >= ticketType.quantity) {
            throw TicketeraException(ErrorMessage.TICKET_TYPE_NOT_AVAILABLE)
        }
    }

    private fun validateSectorAndSeat(sector: UUID, seatId: UUID) {
        if (reservationRepository.countActiveReservationsBySectorAndSeat(sector, seatId) > 0) {
            throw TicketeraException(ErrorMessage.ALREADY_RESERVED)
        }
    }
}