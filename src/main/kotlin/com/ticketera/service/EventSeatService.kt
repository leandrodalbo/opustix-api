package com.ticketera.service

import com.ticketera.dto.eventSeats.NewEventSeatsDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.EventSeat
import com.ticketera.repositories.EventSeatRepository
import com.ticketera.repositories.EventSectorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventSeatService(
    private val eventSeatRepository: EventSeatRepository,
    private val eventSectorRepository: EventSectorRepository,
) {
    @Transactional
    fun generateEventSeats(dto: NewEventSeatsDto): List<EventSeat> {
        val eventSector = eventSectorRepository.findById(dto.sectorId).orElseThrow {
            TicketeraException(ErrorMessage.EVENT_SECTOR_NOT_FOUND)
        }

        return eventSeatRepository.saveAll(
            NewEventSeatsDto
                .newEventSeats(dto, eventSector)
        ).toList()
    }
}