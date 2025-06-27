package com.ticketera.dto.ticketTypes

import com.ticketera.model.Event
import com.ticketera.model.TicketType
import java.time.Instant
import java.util.UUID

data class NewTicketTypeDto(
    val name: String,
    val price: Double,
    val currency: String,
    val startTime: Long?,
    val endTime: Long?,
    val quantity: Int,
    val description: String,
    val eventId: UUID
) {
    companion object {
        fun fromEntity(ticketType: TicketType): NewTicketTypeDto {
            return NewTicketTypeDto(
                ticketType.name,
                ticketType.price,
                ticketType.currency,
                ticketType.saleStart,
                ticketType.saleEnd,
                ticketType.quantity,
                ticketType.description,
                ticketType.event.id
            )
        }

        fun newTicketType(newTicketTypeDto: NewTicketTypeDto, event: Event): TicketType {
            return TicketType(
                id = UUID.randomUUID(),
                newTicketTypeDto.name,
                newTicketTypeDto.price,
                newTicketTypeDto.currency,
                newTicketTypeDto.startTime ?: event.createdAt,
                newTicketTypeDto.endTime ?: event.endTime,
                newTicketTypeDto.quantity,
                newTicketTypeDto.description,
                Instant.now().toEpochMilli(),
                event
            )
        }
    }
}