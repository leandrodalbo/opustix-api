package com.ticketera.service

import com.ticketera.data.PurchaseReservationData
import com.ticketera.data.EventData
import com.ticketera.data.TicketTypeData
import com.ticketera.data.UserData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Reservation
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.EventSeatRepository
import com.ticketera.repositories.EventSectorRepository
import com.ticketera.repositories.PurchaseRepository
import com.ticketera.repositories.ReservationRepository
import com.ticketera.repositories.TicketTypeRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import java.util.Optional

class ReservationServiceTest {

    private val reservationRepository: ReservationRepository = mockk()
    private val purchaseRepository: PurchaseRepository = mockk()
    private val eventRepository: EventRepository = mockk()
    private val ticketTypeRepository: TicketTypeRepository = mockk()
    private val eventSectorRepository: EventSectorRepository = mockk()
    private val eventSeatRepository: EventSeatRepository = mockk()

    private val reservationService = ReservationService(
        reservationRepository,
        purchaseRepository,
        eventRepository,
        ticketTypeRepository,
        eventSectorRepository,
        eventSeatRepository
    )

    @Test
    fun shouldSavePurchaseAndReservations() {
        every { purchaseRepository.save(any()) } returns PurchaseReservationData.purchase
        every { reservationRepository.saveAll(any<List<Reservation>>()) } returns listOf(PurchaseReservationData.reservation)
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TicketTypeData.ticketType)
        every { reservationRepository.countActiveReservationsByTicketTypeId(any()) } returns 0

        val saved =
            reservationService.newReservations(listOf(PurchaseReservationData.newReservationDto), "user@mail.com")

        assertThat(saved.id).isNotNull
        assertThat(saved.reservations).isNotEmpty

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }
        verify { purchaseRepository.save(any()) }
        verify { reservationRepository.saveAll(any<List<Reservation>>()) }
        verify { reservationRepository.countActiveReservationsByTicketTypeId(any()) }
    }

    @Test
    fun shouldNotSaveItWithoutAValidEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                reservationService.newReservations(listOf(PurchaseReservationData.newReservationDto), "user@mail.com")
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldNotSaveItWithoutAValidTicketType() {
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                reservationService.newReservations(listOf(PurchaseReservationData.newReservationDto), "user@mail.com")
            }

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }

    }

    @Test
    fun shouldFetchAllPurchasesByUser() {
        every { purchaseRepository.findPurchasesByUserInfo(anyString()) } returns listOf(PurchaseReservationData.purchase)

        assertThat(reservationService.findPurchasesByUser(UserData.user.email)).isNotEmpty

        verify { purchaseRepository.findPurchasesByUserInfo(anyString()) }
    }

}