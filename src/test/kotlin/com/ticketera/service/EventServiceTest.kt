package com.ticketera.service

import com.ticketera.TestData
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.VenueRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.mockk.just
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class EventServiceTest : TestData() {

    private val eventRepository: EventRepository = mockk()
    private val venueRepository: VenueRepository = mockk()

    private val eventService = EventService(
        eventRepository,
        venueRepository
    )

    @Test
    fun shouldSaveAnewEvent() {
        every { venueRepository.findById(any()) } returns Optional.of(venue)
        every { eventRepository.save(any()) } returns event

        val saved = eventService.addEvent(NewEventDto.fromEntity(event))

        assertThat(saved).isEqualTo(event)

        verify { eventRepository.save(any()) }
        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldNotSaveAnewEventWithoutAVenue() {
        every { venueRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventService.addEvent(NewEventDto.fromEntity(event))
            }

        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { venueRepository.findById(any()) } returns Optional.of(venue)
        every { eventRepository.save(any()) } returns event

        val saved = eventService.updateEvent(UpdateEventDto.fromEntity(event))

        assertThat(saved).isEqualTo(event)

        verify { eventRepository.save(any()) }
        verify { venueRepository.findById(any()) }
        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateNotFoundEvents() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventService.updateEvent(UpdateEventDto.fromEntity(event))
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateEventsWithoutAVenue() {
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { venueRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventService.updateEvent(UpdateEventDto.fromEntity(event))
            }

        verify { eventRepository.findById(any()) }
        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldDeleteAnEvent() {
        every { eventRepository.deleteById(any()) } just runs

        eventService.deleteEvent(event.id)

        verify { eventRepository.deleteById(any()) }
    }

    @Test
    fun shouldFetchAllEvents() {
        every { eventRepository.findAll() } returns listOf(event)

        assertThat(eventService.allEvents())
            .isEqualTo(listOf(event))

        verify { eventRepository.findAll() }
    }
}