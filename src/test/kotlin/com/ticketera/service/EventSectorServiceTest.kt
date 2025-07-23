package com.ticketera.service

import com.ticketera.TestData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.EventSectorRepository
import com.ticketera.repositories.TicketTypeRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class EventSectorServiceTest {

    private val ticketTypeRepository: TicketTypeRepository = mockk()
    private val eventSectorRepository: EventSectorRepository = mockk()

    private val ticketTypeService = EventSectorService(
        eventSectorRepository,
        ticketTypeRepository
    )

    @Test
    fun shouldSaveAnewEventSector() {
        every { eventSectorRepository.save(any()) } returns TestData.eventSector
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TestData.ticketType)

        val saved = ticketTypeService.addEventSector(TestData.newEventSectorDto)

        assertThat(saved).isEqualTo(TestData.eventSector)

        verify { ticketTypeRepository.findById(any()) }
        verify { eventSectorRepository.save(any()) }
    }

    @Test
    fun shouldNotSaveAnEventSectorWithoutATicketType() {
        every { ticketTypeRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.addEventSector(TestData.newEventSectorDto)
            }

        verify { ticketTypeRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateAnEventSector() {
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TestData.ticketType)
        every { eventSectorRepository.findById(any()) } returns Optional.of(TestData.eventSector)
        every { eventSectorRepository.save(any()) } returns TestData.eventSector

        val saved = ticketTypeService.updateEventSector(TestData.updateEventSectorDto)

        assertThat(saved).isEqualTo(TestData.eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateItWithoutTicketType() {
        every { eventSectorRepository.findById(any()) } returns Optional.of(TestData.eventSector)
        every { eventSectorRepository.save(any()) } returns TestData.eventSector

        val saved = ticketTypeService.updateEventSector(TestData.updateEventSectorDto.copy(ticketTypeId = null))

        assertThat(saved).isEqualTo(TestData.eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }

    }

    @Test
    fun shouldNotUpdateItIfTicketTypeNotFound() {
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TestData.ticketType)
        every { eventSectorRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.updateEventSector(TestData.updateEventSectorDto)
            }

        verify { ticketTypeRepository.findById(any()) }
        verify { eventSectorRepository.findById(any()) }
    }

}