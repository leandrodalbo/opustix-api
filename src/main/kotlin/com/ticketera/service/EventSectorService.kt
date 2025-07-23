package com.ticketera.service

import com.ticketera.dto.eventSectors.NewEventSectorDto
import com.ticketera.dto.eventSectors.UpdateEventSectorDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.EventSector
import com.ticketera.model.TicketType
import com.ticketera.repositories.EventSectorRepository
import com.ticketera.repositories.TicketTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventSectorService(
    private val eventSectorRepository: EventSectorRepository,
    private val ticketTypeRepository: TicketTypeRepository
) {

    @Transactional
    fun addEventSector(newEventSectorDto: NewEventSectorDto): EventSector {
        val ticketType = ticketTypeRepository.findById(newEventSectorDto.ticketTypeId).orElseThrow {
            TicketeraException(ErrorMessage.TICKET_TYPE_NOT_FOUND)
        }

        return eventSectorRepository.save(
            NewEventSectorDto.newEventSector(newEventSectorDto, ticketType)
        )
    }

    @Transactional
    fun updateEventSector(updateEventSectorDto: UpdateEventSectorDto): EventSector {

        val ticketType: TicketType? = updateEventSectorDto.ticketTypeId?.let {
            ticketTypeRepository.findById(updateEventSectorDto.ticketTypeId)
                .orElseThrow {
                    TicketeraException(ErrorMessage.TICKET_TYPE_NOT_FOUND)
                }

        }

        val eventSector: EventSector = eventSectorRepository.findById(updateEventSectorDto.id)
            .orElseThrow {
                TicketeraException(ErrorMessage.EVENT_SECTOR_NOT_FOUND)
            }

        return ticketType?.let {
            eventSectorRepository.save(
                UpdateEventSectorDto.updatedEventSector(updateEventSectorDto, eventSector, it)
            )
        } ?: eventSectorRepository.save(
            UpdateEventSectorDto.updatedEventSector(updateEventSectorDto, eventSector, eventSector.ticketType)
        )
    }
}