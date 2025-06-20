package com.ticketera.ticketera.service

import com.ticketera.ticketera.dto.NewEventDto
import com.ticketera.ticketera.dto.UpdateEventDto
import com.ticketera.ticketera.exceptions.ErrorMessage
import com.ticketera.ticketera.exceptions.TicketeraException
import com.ticketera.ticketera.model.Event
import com.ticketera.ticketera.repositories.EventRepository
import com.ticketera.ticketera.repositories.VenueRepository
import java.time.Instant
import java.util.UUID

class EventService(
    private val eventRepository: EventRepository,
    private val venueRepository: VenueRepository
) {

    fun addEvent(newEventDto: NewEventDto) {
        val venue = venueRepository.findById(newEventDto.venueId).orElseThrow {
            TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
        }

        eventRepository.save(
            Event(
                id = UUID.randomUUID(),
                title = newEventDto.title,
                description = newEventDto.description,
                startTime = newEventDto.startTime,
                endTime = newEventDto.endTime,
                capacity = newEventDto.capacity,
                venue = venue,
                createdAt = Instant.now().toEpochMilli()

            )
        )

    }

    fun updateEvent(updateEventDto: UpdateEventDto) {

        val event = eventRepository.findById(updateEventDto.id)
            .orElseThrow {
                TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
            }

        val venue = updateEventDto.venueId?.let {
            venueRepository.findById(it).orElseThrow {
                TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
            }
        } ?: event.venue

        eventRepository.save(
            Event(
                id = updateEventDto.id,
                title = updateEventDto.title ?: event.title,
                description = updateEventDto.description ?: event.description,
                startTime = updateEventDto.startTime ?: event.startTime,
                endTime = updateEventDto.endTime ?: event.endTime,
                capacity = updateEventDto.capacity ?: event.capacity,
                venue = venue,
                createdAt = event.createdAt
            )
        )

    }

    fun deleteEvent(uuid: UUID) = eventRepository.deleteById(uuid)

    fun allEvents() = eventRepository.findAll()
}