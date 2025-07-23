package com.ticketera.data

import com.ticketera.dto.events.EventDetailsDto
import com.ticketera.dto.events.EventDto
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
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


        val eventDto = EventDto.Companion.fromEntity(event)


        val eventDetailsDto =
            EventDetailsDto.Companion.fromEntities(
                event,
                VenueData.venue,
                BannerData.bannerImageUrl,
                listOf(TicketTypeData.ticketType),
                listOf(EventSectorData.eventSector),
                listOf(EventSeatData.eventSeat)
            )

        val updateEventDto = UpdateEventDto(
            id = event.id,
            "event-new-title",
            "event-updated",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            1000,
            "concert",
            VenueData.venue.id
        )

    }
}