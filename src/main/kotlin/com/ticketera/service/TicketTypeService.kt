package com.ticketera.service


import com.ticketera.dto.ticketTypes.NewTicketTypeDto
import com.ticketera.dto.ticketTypes.UpdateTicketTypeDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Event
import com.ticketera.model.TicketType
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.TicketTypeRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TicketTypeService(
    private val ticketTypeRepository: TicketTypeRepository,
    private val eventRepository: EventRepository
) {

    fun addTicketType(newTicketTypeDto: NewTicketTypeDto): TicketType {
        val event = eventRepository.findById(newTicketTypeDto.eventId).orElseThrow {
            TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
        }

        return ticketTypeRepository.save(
            NewTicketTypeDto.newTicketType(newTicketTypeDto, event)
        )
    }

    fun updateTicketType(updateTicketTypeDto: UpdateTicketTypeDto): TicketType {

        val event: Event? = updateTicketTypeDto.eventId?.let {
            eventRepository.findById(updateTicketTypeDto.eventId)
                .orElseThrow {
                    TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
                }

        }

        val ticketType: TicketType = ticketTypeRepository.findById(updateTicketTypeDto.id)
            .orElseThrow {
                TicketeraException(ErrorMessage.TICKET_TYPE_NOT_FOUND)
            }

        return event?.let {
            ticketTypeRepository.save(
                UpdateTicketTypeDto.updatedTicketType(updateTicketTypeDto, ticketType, it)
            )
        } ?: ticketTypeRepository.save(
            UpdateTicketTypeDto.updatedTicketType(updateTicketTypeDto, ticketType, ticketType.event)
        )

    }

    fun deleteByEventId(uuid: UUID) = ticketTypeRepository.deleteByEventId(uuid)

    fun findByEventId(uuid: UUID) = ticketTypeRepository.findAllByEventId(uuid)
}