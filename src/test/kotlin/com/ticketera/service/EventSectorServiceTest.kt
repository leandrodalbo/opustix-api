package com.ticketera.service

import com.ticketera.TestData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.EventSectorRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.just
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class EventSectorServiceTest : TestData() {

    private val eventRepository: EventRepository = mockk()
    private val eventSectorRepository: EventSectorRepository = mockk()

    private val ticketTypeService = EventSectorService(
        eventSectorRepository,
        eventRepository
    )

    @Test
    fun shouldSaveAnewTicketType() {
        every { eventSectorRepository.save(any()) } returns eventSector
        every { eventRepository.findById(any()) } returns Optional.of(event)

        val saved = ticketTypeService.addEventSector(newEventSectorDto)

        assertThat(saved).isEqualTo(eventSector)

        verify { eventRepository.findById(any()) }
        verify { eventSectorRepository.save(any()) }
    }

    @Test
    fun shouldNotSaveATicketTypeWithoutAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.addEventSector(newEventSectorDto)
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateATicketType() {
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { eventSectorRepository.findById(any()) } returns Optional.of(eventSector)
        every { eventSectorRepository.save(any()) } returns eventSector

        val saved = ticketTypeService.updateEventSector(updateEventSectorDto)

        assertThat(saved).isEqualTo(eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }
        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateItWithoutEvents() {
        every { eventSectorRepository.findById(any()) } returns Optional.of(eventSector)
        every { eventSectorRepository.save(any()) } returns eventSector

        val saved = ticketTypeService.updateEventSector(updateEventSectorDto.copy(eventId = null))

        assertThat(saved).isEqualTo(eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateItIfNotFound() {
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { eventSectorRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.updateEventSector(updateEventSectorDto)
            }

        verify { eventRepository.findById(any()) }
        verify { eventSectorRepository.findById(any()) }
    }

    @Test
    fun shouldDeleteByEventId() {
        every { eventSectorRepository.deleteByEventId(any()) } just runs

        ticketTypeService.deleteByEventId(eventId)

        verify { eventSectorRepository.deleteByEventId(any()) }
    }

    @Test
    fun shouldFetchAllByEventId() {
        every { eventSectorRepository.findAllByEventId(any()) } returns listOf(eventSector)

        assertThat(ticketTypeService.findByEventId(eventId))
            .isEqualTo(listOf(eventSector))

        verify { eventSectorRepository.findAllByEventId(any()) }
    }
}