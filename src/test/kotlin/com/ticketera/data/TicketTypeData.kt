package com.ticketera.data

import com.ticketera.dto.ticketTypes.NewTicketTypeDto
import com.ticketera.dto.ticketTypes.TicketTypeDto
import com.ticketera.dto.ticketTypes.UpdateTicketTypeDto
import com.ticketera.model.TicketType
import java.time.Instant
import java.util.UUID

class TicketTypeData {
    companion object {
        val ticketType = TicketType(
            UUID.randomUUID(),
            "GOLDEN",
            132.44,
            "ARS",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            500,
            "Golden Ticket",
            Instant.now().toEpochMilli(),
            EventData.event
        )


        val ticketTypeDto = TicketTypeDto(
            ticketType.id,
            ticketType.name,
            ticketType.price,
            ticketType.currency,
            ticketType.saleStart,
            ticketType.saleEnd,
            ticketType.description
        )

        val newTicketTypeDto = NewTicketTypeDto(
            "GOLDEN",
            150.0,
            "ARS",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            500,
            "golden-ticket",
            EventData.event.id
        )

        val updateTicketTypeDto = UpdateTicketTypeDto(
            ticketType.id,
            "GOLDEN",
            150.0,
            "ARS",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            500,
            "golden-ticket",
            EventData.event.id
        )

    }

}