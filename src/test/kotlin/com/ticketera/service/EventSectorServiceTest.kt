package com.ticketera.service

import com.ticketera.data.EventSectorData
import com.ticketera.data.TicketTypeData
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
        every { eventSectorRepository.save(any()) } returns EventSectorData.eventSector
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TicketTypeData.ticketType)

        val saved = ticketTypeService.addEventSector(EventSectorData.newEventSectorDto)

        assertThat(saved).isEqualTo(EventSectorData.eventSector)

        verify { ticketTypeRepository.findById(any()) }
        verify { eventSectorRepository.save(any()) }
    }

    @Test
    fun shouldNotSaveAnEventSectorWithoutATicketType() {
        every { ticketTypeRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.addEventSector(EventSectorData.newEventSectorDto)
            }

        verify { ticketTypeRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateAnEventSector() {
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TicketTypeData.ticketType)
        every { eventSectorRepository.findById(any()) } returns Optional.of(EventSectorData.eventSector)
        every { eventSectorRepository.save(any()) } returns EventSectorData.eventSector

        val saved = ticketTypeService.updateEventSector(EventSectorData.updateEventSectorDto)

        assertThat(saved).isEqualTo(EventSectorData.eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateItWithoutTicketType() {
        every { eventSectorRepository.findById(any()) } returns Optional.of(EventSectorData.eventSector)
        every { eventSectorRepository.save(any()) } returns EventSectorData.eventSector

        val saved = ticketTypeService.updateEventSector(EventSectorData.updateEventSectorDto.copy(ticketTypeId = null))

        assertThat(saved).isEqualTo(EventSectorData.eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }

    }

    @Test
    fun shouldNotUpdateItIfTicketTypeNotFound() {
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TicketTypeData.ticketType)
        every { eventSectorRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.updateEventSector(EventSectorData.updateEventSectorDto)
            }

        verify { ticketTypeRepository.findById(any()) }
        verify { eventSectorRepository.findById(any()) }
    }

}