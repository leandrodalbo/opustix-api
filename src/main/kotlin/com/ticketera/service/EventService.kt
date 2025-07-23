package com.ticketera.service

import com.ticketera.dto.events.EventDetailsDto
import com.ticketera.dto.events.EventDto
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Event
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.TicketTypeRepository
import com.ticketera.repositories.VenueRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val venueRepository: VenueRepository,
    private val ticketTypeRepository: TicketTypeRepository
) {

    @Transactional
    fun addEvent(newEventDto: NewEventDto): Event {
        val venue = venueRepository.findById(newEventDto.venueId).orElseThrow {
            TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
        }

        return eventRepository.save(
            NewEventDto.newEvent(newEventDto, venue)
        )
    }

    @Transactional
    fun updateEvent(updateEventDto: UpdateEventDto): Event {
        val event = eventRepository.findById(updateEventDto.id)
            .orElseThrow {
                TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
            }

        val venue = updateEventDto.venueId?.let {
            venueRepository.findById(it).orElseThrow {
                TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
            }
        } ?: event.venue

        return eventRepository.save(
            UpdateEventDto.updatedEvent(
                updateEventDto,
                venue, event
            )
        )
    }

    @Transactional(readOnly = true)
    fun allEvents() = eventRepository.findAll().filter { !it.hasFinished() }
        .map { EventDto.fromEntity(it) }

    @Transactional(readOnly = true)
    fun eventDetails(eventId: UUID): EventDetailsDto {
        val ticketTypes = ticketTypeRepository.findTicketTypesWithSectorsAndSeatsByEventId(eventId)
            .ifEmpty { throw TicketeraException(ErrorMessage.DATABASE_ERROR) }

        val event = ticketTypes.first().event
        val mainBannerUrl = event.banners.firstOrNull { it.isMain }?.imageUrl ?: ""
        val sectors = ticketTypes.flatMap { it.sectors }.toSet()
        val seats = sectors.flatMap { it.seats }.toSet()

        return EventDetailsDto.fromEntities(
            event,
            event.venue,
            mainBannerUrl,
            ticketTypes,
            sectors,
            seats
        )
    }
}