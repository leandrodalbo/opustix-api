package com.ticketera.dto

import com.ticketera.model.Event
import com.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import java.time.Instant
import java.util.UUID
import kotlin.test.Test

class UpdateEventDtoTest {
    val venue = Venue(
        UUID.randomUUID(),
        "venue-0",
        address = "Road x at 1324",
        Instant.now().toEpochMilli()
    )

    val newVenue = Venue(
        UUID.randomUUID(),
        "venue-1",
        address = "Road Y at 1324",
        Instant.now().toEpochMilli()
    )

    val dto = UpdateEventDto(
        id = UUID.randomUUID(),
        "event-new-title",
        "event-updated",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        1000,
        newVenue.id
    )

    val event = Event(
        id = dto.id,
        "event-new-title",
        "event-updated",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        1000,
        venue,
        Instant.now().toEpochMilli()
    )

    @Test
    fun shouldGetAnUpdatedEventFromDto() {
        val updatedEvent = UpdateEventDto.updatedEvent(
            dto, newVenue, event
        )

        assertThat(updatedEvent.id).isEqualTo(dto.id)
        assertThat(updatedEvent.title).isEqualTo(dto.title)
        assertThat(updatedEvent.venue.id).isEqualTo(dto.venueId)

    }

    @Test
    fun shouldGetDtoFromTheEntity() {

        assertThat(
            UpdateEventDto.fromEntity(event)
        ).isInstanceOf(UpdateEventDto::class.java)
    }
}