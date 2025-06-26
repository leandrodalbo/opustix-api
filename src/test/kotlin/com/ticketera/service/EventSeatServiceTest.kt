package com.ticketera.service

import com.ticketera.TestData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.EventSeat
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.EventSeatRepository
import com.ticketera.repositories.EventSectorRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.runs
import io.mockk.just
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class EventSeatServiceTest : TestData() {

    private val eventRepository: EventRepository = mockk()
    private val eventSectorRepository: EventSectorRepository = mockk()
    private val eventSeatRepository: EventSeatRepository = mockk()

    private val eventSeatService = EventSeatService(
        eventSeatRepository,
        eventSectorRepository,
        eventRepository
    )

    @Test
    fun shouldGenerateTheSeatsAnSaveThem() {
        every { eventSeatRepository.saveAll(any<List<EventSeat>>()) } returns listOf(eventSeat)
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { eventSectorRepository.findById(any()) } returns Optional.of(eventSector)

        val saved = eventSeatService.generateEventSeats(newEventSeatsDto)

        assertThat(saved).isEqualTo(listOf(eventSeat))

        verify { eventRepository.findById(any()) }
        verify { eventSectorRepository.findById(any()) }
        verify { eventSeatRepository.saveAll(any<List<EventSeat>>()) }
    }

    @Test
    fun shouldGenerateTheSeatsWithoutASector() {
        every { eventSeatRepository.saveAll(any<List<EventSeat>>()) } returns listOf(eventSeat)
        every { eventRepository.findById(any()) } returns Optional.of(event)

        val saved = eventSeatService.generateEventSeats(newEventSeatsDto.copy(sectorId = null))

        assertThat(saved).isEqualTo(listOf(eventSeat))

        verify { eventRepository.findById(any()) }
        verify { eventSeatRepository.saveAll(any<List<EventSeat>>()) }
    }

    @Test
    fun shouldNotSaveSeatsWithoutAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventSeatService.generateEventSeats(newEventSeatsDto)
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldNotSaveSeatsWithoutASectorWhenTheSectorIsPresent() {
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { eventSectorRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventSeatService.generateEventSeats(newEventSeatsDto)
            }

        verify { eventRepository.findById(any()) }
        verify { eventSectorRepository.findById(any()) }
    }

    @Test
    fun shouldDeleteTheSeatsWithoutASector() {
        every { eventSeatRepository.deleteByEventId(any()) } just runs

        eventSeatService.deleteSeats(eventId)

        verify { eventSeatRepository.deleteByEventId(any()) }
    }

    @Test
    fun shouldDeleteTheSeatsWithASector() {
        every { eventSeatRepository.deleteByEventIdAndSectorId(any(), any()) } just runs

        eventSeatService.deleteSeats(eventId, eventSectorId)

        verify { eventSeatRepository.deleteByEventIdAndSectorId(any(), any()) }
    }

    @Test
    fun shouldFindTheSeatsWithoutASector() {
        every { eventSeatRepository.findAllByEventId(any()) } returns listOf(eventSeat)

        eventSeatService.findSeats(eventId)

        verify { eventSeatRepository.findAllByEventId(any()) }
    }

    @Test
    fun shouldFindTheSeatsWithASector() {
        every { eventSeatRepository.findAllByEventIdAndSectorId(any(), any()) } returns listOf(eventSeat)

        eventSeatService.findSeats(eventId, eventSectorId)

        verify { eventSeatRepository.findAllByEventIdAndSectorId(any(), any()) }
    }
}