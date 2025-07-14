package com.ticketera.service

import com.ticketera.TestData
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
        every { purchaseRepository.save(any()) } returns TestData.purchase
        every { reservationRepository.saveAll(any<List<Reservation>>()) } returns listOf(TestData.reservation)
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TestData.ticketType)

        val saved = reservationService.newReservations(listOf(TestData.newReservationDto), "user@mail.com")

        assertThat(saved.id).isNotNull
        assertThat(saved.reservations).isNotEmpty

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }
        verify { purchaseRepository.save(any()) }
        verify { reservationRepository.saveAll(any<List<Reservation>>()) }

    }

    @Test
    fun shouldNotSaveItWithoutAValidEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                reservationService.newReservations(listOf(TestData.newReservationDto), "user@mail.com")
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldNotSaveItWithoutAValidTicketType() {
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                reservationService.newReservations(listOf(TestData.newReservationDto), "user@mail.com")
            }

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }

    }

}