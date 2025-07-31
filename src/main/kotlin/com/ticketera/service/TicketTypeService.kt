package com.ticketera.service

import com.ticketera.dto.generic.Completed
import com.ticketera.dto.ticketTypes.NewTicketTypeDto
import com.ticketera.dto.ticketTypes.UpdateTicketTypeDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Event
import com.ticketera.model.TicketType
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.TicketTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TicketTypeService(
    private val ticketTypeRepository: TicketTypeRepository,
    private val eventRepository: EventRepository
) {

    @Transactional
    fun addTicketType(newTicketTypeDto: NewTicketTypeDto): Completed {
        val event = eventRepository.findById(newTicketTypeDto.eventId).orElseThrow {
            TicketeraException(ErrorMessage.EVENT_NOT_FOUND)
        }

        return ticketTypeRepository.save(
            NewTicketTypeDto.newTicketType(newTicketTypeDto, event)
        ).let { toCompleted(it) }
    }

    @Transactional
    fun updateTicketType(updateTicketTypeDto: UpdateTicketTypeDto): Completed {

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

        return event?.let { it ->
            ticketTypeRepository.save(
                UpdateTicketTypeDto.updatedTicketType(updateTicketTypeDto, ticketType, it)
            ).let { tt -> toCompleted(tt) }
        } ?: ticketTypeRepository.save(
            UpdateTicketTypeDto.updatedTicketType(updateTicketTypeDto, ticketType, ticketType.event)
        ).let { tt -> toCompleted(tt) }

    }

    private fun toCompleted(ticketType: TicketType) = Completed(ticketType.id)
}