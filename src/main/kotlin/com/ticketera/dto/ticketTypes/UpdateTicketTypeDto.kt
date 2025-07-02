package com.ticketera.dto.ticketTypes

import com.ticketera.model.Event
import com.ticketera.model.TicketType
import java.util.UUID

data class UpdateTicketTypeDto(
    val id: UUID,
    val name: String?,
    val price: Double?,
    val currency: String?,
    val startTime: Long?,
    val endTime: Long?,
    val quantity: Int?,
    val description: String?,
    val eventId: UUID?
) {
    companion object {

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