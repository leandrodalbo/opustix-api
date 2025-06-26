package com.ticketera.service

import com.ticketera.dto.eventSeats.NewEventSeatsDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.EventSeatRepository
import com.ticketera.repositories.EventSectorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class EventSeatService(
    private val eventSeatRepository: EventSeatRepository,
    private val eventSectorRepository: EventSectorRepository,
    private val eventRepository: EventRepository
) {

    fun generateEventSeats(dto: NewEventSeatsDto): List<EventSeat> {
        val event = eventRepository.findById(dto.eventId).orElseThrow {
            TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
        }

        val sector: EventSector? =
            dto.sectorId?.let {
                eventSectorRepository.findById(it)
                    .orElseThrow {
                        TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
                    }
            }

        return eventSeatRepository.saveAll(
            NewEventSeatsDto
                .newEventSeats(dto, event, sector)
        ).toList()
    }

    @Transactional
    fun deleteSeats(eventId: UUID, sectorId: UUID?) {
        sectorId?.let {
            eventSeatRepository.deleteByEventIdAndSectorId(eventId, it)
        } ?: eventSeatRepository.deleteByEventId(eventId)
    }


    fun findSeats(eventId: UUID, sectorId: UUID?) {
        sectorId?.let {
            eventSeatRepository.findAllByEventIdAndSectorId(eventId, it)
        } ?: eventSeatRepository.findAllByEventId(eventId)
    }
}