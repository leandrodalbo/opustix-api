package com.ticketera.dto.ticketTypes

import com.ticketera.model.Event
import com.ticketera.model.TicketType
import java.math.BigDecimal
import java.util.UUID

data class UpdateTicketTypeDto(
    val id: UUID,
    val name: String?,
    val price: BigDecimal?,
    val currency: String?,
    val startTime: Long?,
    val endTime: Long?,
    val quantity: Int?,
    val description: String?,
    val eventId: UUID?
) {
    companion object {
        fun fromEntity(ticketType: TicketType): UpdateTicketTypeDto {
            return UpdateTicketTypeDto(
                id = ticketType.id,
                name = ticketType.name,
                price = ticketType.price,
                currency = ticketType.currency,
                startTime = ticketType.saleStart,
                endTime = ticketType.saleEnd,
                quantity = ticketType.quantity,
                description = ticketType.description,
                eventId = ticketType.event.id
            )
        }

        fun updatedTicketType(dto: UpdateTicketTypeDto, ticketType: TicketType, event: Event): TicketType {
            return TicketType(
                ticketType.id,
                dto.name ?: ticketType.name,
                dto.price ?: ticketType.price,
                dto.currency ?: ticketType.currency,
                dto.startTime ?: ticketType.saleStart,
                dto.endTime ?: ticketType.saleEnd,
                dto.quantity ?: ticketType.quantity,
                dto.description ?: ticketType.description,
                ticketType.createdAt,
                event
            )
        }
    }
}