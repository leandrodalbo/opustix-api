package com.ticketera.service

import com.ticketera.data.EventData
import com.ticketera.data.TicketTypeData
import com.ticketera.data.VenueData

import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.TicketTypeRepository
import com.ticketera.repositories.VenueRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class EventServiceTest {

    private val eventRepository: EventRepository = mockk()
    private val venueRepository: VenueRepository = mockk()
    private val ticketTypeRepository: TicketTypeRepository = mockk()

    private val eventService = EventService(
        eventRepository,
        venueRepository,
        ticketTypeRepository
    )

    @Test
    fun shouldSaveAnewEvent() {
        every { venueRepository.findById(any()) } returns Optional.of(VenueData.venue)
        every { eventRepository.save(any()) } returns EventData.event

        val saved = eventService.addEvent(EventData.newEventDto)

        assertThat(saved).isEqualTo(EventData.event)

        verify { eventRepository.save(any()) }
        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldNotSaveAnewEventWithoutAVenue() {
        every { venueRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventService.addEvent(EventData.newEventDto)
            }

        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)
        every { venueRepository.findById(any()) } returns Optional.of(VenueData.venue)
        every { eventRepository.save(any()) } returns EventData.event

        val saved = eventService.updateEvent(EventData.updateEventDto)

        assertThat(saved).isEqualTo(EventData.event)

        verify { eventRepository.save(any()) }
        verify { venueRepository.findById(any()) }
        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateNotFoundEvents() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventService.updateEvent(EventData.updateEventDto)
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateEventsWithoutAVenue() {
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)
        every { venueRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventService.updateEvent(EventData.updateEventDto)
            }

        verify { eventRepository.findById(any()) }
        verify { venueRepository.findById(any()) }
    }


    @Test
    fun shouldFetchAllEvents() {
        every { eventRepository.findAll() } returns listOf(EventData.event)

        assertThat(eventService.allEvents())
            .isEqualTo(listOf(EventData.eventDto))

        verify { eventRepository.findAll() }
    }

    @Test
    fun shouldFetchAnEventDetails() {
        every { ticketTypeRepository.findTicketTypesWithSectorsAndSeatsByEventId(EventData.event.id) } returns
                listOf(TicketTypeData.ticketType)

        assertThat(eventService.eventDetails(EventData.event.id))
            .isEqualTo(
                EventData.eventDetailsDto.copy(
                    mainBanner = "",
                    venueDto = VenueData.venueDto,
                    ticketTypes = listOf(TicketTypeData.ticketTypeDto),
                    sectors = emptyList(),
                    seats = emptyList()
                )
            )

        verify { ticketTypeRepository.findTicketTypesWithSectorsAndSeatsByEventId(EventData.event.id) }

    }
}