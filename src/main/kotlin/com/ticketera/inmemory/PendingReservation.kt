package com.ticketera.aux

import com.ticketera.model.Event
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import com.ticketera.model.TicketType

data class PendingReservation(
    val event: Event,
    val ticketType: TicketType,
    val sector: EventSector?,
    val seat: EventSeat?,
    val price: Double
)