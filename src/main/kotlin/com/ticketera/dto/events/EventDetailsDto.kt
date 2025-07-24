package com.ticketera.dto.events

import com.ticketera.dto.eventSeats.EventSeatDto
import com.ticketera.dto.eventSectors.EventSectorDto
import com.ticketera.dto.ticketTypes.TicketTypeDto
import com.ticketera.dto.venues.VenueDto
import com.ticketera.model.Event
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import com.ticketera.model.TicketType
import com.ticketera.model.Venue
import java.util.UUID


data class EventDetailsDto(
    val id: UUID,
    val title: String,
    val description: String?,
    val startTime: Long,
    val endTime: Long,
    val capacity: Int,
    val mainBanner: String,
    val venueDto: VenueDto,
    val ticketTypes: List<TicketTypeDto>,
    val sectors: List<EventSectorDto>,
    val seats: List<EventSeatDto>
) {

    companion object {
        fun fromEntities(
            event: Event,
            venue: Venue,
            mainBannerUrl: String,
            ticketType: List<TicketType>,
            sector: Set<EventSector>,
            seat: Set<EventSeat>
        ): EventDetailsDto {
            return EventDetailsDto(
                id = event.id,
                title = event.title,
                description = event.description,
                startTime = event.startTime,
                endTime = event.endTime,
                capacity = event.capacity,
                mainBanner = mainBannerUrl,
                venueDto = VenueDto.fromEntity(venue),
                ticketTypes = ticketType.map { TicketTypeDto.fromEntity(it) },
                sectors = sector.map { EventSectorDto.fromEntities(it, it.ticketType) },
                seats = seat.map { EventSeatDto.fromEntity(it) }
            )
        }
    }
}
