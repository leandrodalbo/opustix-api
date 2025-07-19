package com.ticketera.dto.ticketTypes

import com.ticketera.model.TicketType
import java.util.UUID

data class TicketTypeDto(
    val id: UUID,
    val name: String,
    val price: Double,
    val currency: String,
    val saleStart: Long,
    val saleEnd: Long,
    val description: String,

    ) {
    companion object {
        fun fromEntity(ticketType: TicketType) =
            TicketTypeDto(
                ticketType.id,
                ticketType.name,
                ticketType.price,
                ticketType.currency,
                ticketType.saleStart,
                ticketType.saleEnd,
                ticketType.description
            )
    }
}