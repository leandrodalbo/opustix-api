package com.ticketera.ticketera.service

import com.ticketera.ticketera.dto.NewEventDto
import com.ticketera.ticketera.dto.UpdateEventDto
import com.ticketera.ticketera.exceptions.ErrorMessage
import com.ticketera.ticketera.exceptions.TicketeraException
import com.ticketera.ticketera.model.Event
import com.ticketera.ticketera.repositories.EventRepository
import com.ticketera.ticketera.repositories.VenueRepository
import java.util.UUID

class EventService(
    private val eventRepository: EventRepository,
    private val venueRepository: VenueRepository
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

    fun allEvents() = eventRepository.findAll()
}