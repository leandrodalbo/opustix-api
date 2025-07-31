package com.ticketera.data

import com.ticketera.dto.events.EventDetailsDto
import com.ticketera.dto.events.EventDto
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
import com.ticketera.dto.venues.VenueDto
import com.ticketera.model.Event
import java.time.Instant
import java.util.UUID

class EventData {
    companion object {

        val event = Event(
            UUID.randomUUID(),
            "event-x",
            "event-x",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            1000,
            "concert",
            VenueData.venue,
            Instant.now().toEpochMilli()
        )

        val newEventDto = NewEventDto(
            "event-x",
            "event-x",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            1000,
            "concert",
            VenueData.venue.id
        )


        val eventDto = EventDto(
            event.id,
            event.title,
            event.description,
            event.startTime,
            event.endTime,
            event.capacity,
            event.category,
            VenueDto.fromEntity(event.venue),
            emptyList()
        )


        val eventDetailsDto =
            EventDetailsDto(
                event.id,
                event.title,
                event.description,
                event.startTime,
                event.endTime,
                event.capacity,
                BannerData.bannerImageUrl,
                VenueData.venueDto,
                listOf(TicketTypeData.ticketTypeDto),
                listOf(EventSectorData.eventSectorDto),
                listOf(EventSeatData.eventSeatDto)
            )

        val updateEventDto = UpdateEventDto(
            id = event.id,
            "event-new-title",
            "event-updated",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            1000,
            "concert",
            event.venue.id
        )
    }
}