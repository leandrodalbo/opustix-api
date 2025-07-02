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

class EventSectorServiceTest {

    private val eventRepository: EventRepository = mockk()
    private val eventSectorRepository: EventSectorRepository = mockk()

    private val ticketTypeService = EventSectorService(
        eventSectorRepository,
        eventRepository
    )

    @Test
    fun shouldSaveAnewEventSector() {
        every { eventSectorRepository.save(any()) } returns TestData.eventSector
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)

        val saved = ticketTypeService.addEventSector(TestData.newEventSectorDto)

        assertThat(saved).isEqualTo(TestData.eventSector)

        verify { eventRepository.findById(any()) }
        verify { eventSectorRepository.save(any()) }
    }

    @Test
    fun shouldNotSaveAnEventSectorWithoutAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.addEventSector(TestData.newEventSectorDto)
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateAnEventSector() {
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)
        every { eventSectorRepository.findById(any()) } returns Optional.of(TestData.eventSector)
        every { eventSectorRepository.save(any()) } returns TestData.eventSector

        val saved = ticketTypeService.updateEventSector(TestData.updateEventSectorDto)

        assertThat(saved).isEqualTo(TestData.eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }
        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateItWithoutEvents() {
        every { eventSectorRepository.findById(any()) } returns Optional.of(TestData.eventSector)
        every { eventSectorRepository.save(any()) } returns TestData.eventSector

        val saved = ticketTypeService.updateEventSector(TestData.updateEventSectorDto.copy(eventId = null))

        assertThat(saved).isEqualTo(TestData.eventSector)

        verify { eventSectorRepository.save(any()) }
        verify { eventSectorRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateItIfNotFound() {
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)
        every { eventSectorRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.updateEventSector(TestData.updateEventSectorDto)
            }

        verify { eventRepository.findById(any()) }
        verify { eventSectorRepository.findById(any()) }
    }

    @Test
    fun shouldDeleteByEventId() {
        every { eventSectorRepository.deleteByEventId(any()) } just runs

        ticketTypeService.deleteByEventId(TestData.event.id)

        verify { eventSectorRepository.deleteByEventId(any()) }
    }

    @Test
    fun shouldFetchAllByEventId() {
        every { eventSectorRepository.findAllByEventId(any()) } returns listOf(TestData.eventSector)

        assertThat(ticketTypeService.findByEventId(TestData.event.id))
            .isEqualTo(listOf(TestData.eventSector))

        verify { eventSectorRepository.findAllByEventId(any()) }
    }
}