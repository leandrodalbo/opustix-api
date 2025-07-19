package com.ticketera.dto.events

import com.ticketera.dto.eventSeats.EventSeatDto
import com.ticketera.dto.eventSectors.EventSectorDto
import com.ticketera.dto.ticketTypes.TicketTypeDto
import java.util.UUID

data class EventDetailsDto(
    val id: UUID,
    val title: String,
    val description: String?,
    val startTime: Long,
    val endTime: Long,
    val capacity: Int,
    val ticketTypes: List<TicketTypeDto>,
    val sectors: List<EventSectorDto>,
    val seats: List<EventSeatDto>
)
