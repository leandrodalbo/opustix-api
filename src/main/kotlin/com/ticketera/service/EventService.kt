package com.ticketera.service

import com.ticketera.dto.events.EventDetailsDto
import com.ticketera.dto.events.EventDto
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Event
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.EventSeatRepository
import com.ticketera.repositories.EventSectorRepository
import com.ticketera.repositories.TicketTypeRepository
import com.ticketera.repositories.VenueRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val venueRepository: VenueRepository,
    private val ticketTypeRepository: TicketTypeRepository,
    private val eventSectorRepository: EventSectorRepository,
    private val eventSeatRepository: EventSeatRepository
) {

    fun addEvent(newEventDto: NewEventDto): Event {
        val venue = venueRepository.findById(newEventDto.venueId).orElseThrow {
            TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
        }

        return eventRepository.save(
            NewEventDto.newEvent(newEventDto, venue)
        )
    }

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

    fun deleteEvent(uuid: UUID) = eventRepository.deleteById(uuid)

    fun allEvents() = eventRepository.findAll().filter { !it.hasFinished() }
        .map { EventDto.fromEntity(it) }


    fun eventDetails(eventId: UUID): EventDetailsDto {
        val event = eventRepository.findById(eventId)
            .orElseThrow { TicketeraException(ErrorMessage.EVENT_NOT_FOUND) }

        val venue = venueRepository.findByEventId(eventId)
        val ticketTypes = ticketTypeRepository.findAllByEventId(eventId)
        val sectors = eventSectorRepository.findAllByEventId(eventId)
        val seats = eventSeatRepository.findAllByEventId(eventId)

        return EventDetailsDto.fromEntities(
            event, venue, ticketTypes,
            sectors, seats
        )
    }
}