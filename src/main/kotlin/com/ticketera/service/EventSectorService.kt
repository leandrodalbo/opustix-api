package com.ticketera.service

import com.ticketera.dto.eventSectors.NewEventSectorDto
import com.ticketera.dto.eventSectors.UpdateEventSectorDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Event
import com.ticketera.model.EventSector
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.EventSectorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class EventSectorService(
    private val eventSectorRepository: EventSectorRepository,
    private val eventRepository: EventRepository
) {

    fun addEventSector(newEventSectorDto: NewEventSectorDto): EventSector {
        val event = eventRepository.findById(newEventSectorDto.eventId).orElseThrow {
            TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
        }

        return eventSectorRepository.save(
            NewEventSectorDto.newEventSector(newEventSectorDto, event)
        )
    }

    fun updateEventSector(updateEventSectorDto: UpdateEventSectorDto): EventSector {

        val event: Event? = updateEventSectorDto.eventId?.let {
            eventRepository.findById(updateEventSectorDto.eventId)
                .orElseThrow {
                    TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
                }

        }

        val eventSector: EventSector = eventSectorRepository.findById(updateEventSectorDto.id)
            .orElseThrow {
                TicketeraException(ErrorMessage.EVENT_SECTOR_NOT_FOUND)
            }

        return event?.let {
            eventSectorRepository.save(
                UpdateEventSectorDto.updatedEventSector(updateEventSectorDto, eventSector, it)
            )
        } ?: eventSectorRepository.save(
            UpdateEventSectorDto.updatedEventSector(updateEventSectorDto, eventSector, eventSector.event)
        )

    }

    @Transactional
    fun deleteByEventId(uuid: UUID) = eventSectorRepository.deleteByEventId(uuid)

    fun findByEventId(uuid: UUID) = eventSectorRepository.findAllByEventId(uuid)
}