package com.ticketera.ticketera.dto

import com.ticketera.ticketera.model.Event
import com.ticketera.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import java.time.Instant
import java.util.UUID
import kotlin.test.Test

class NewEventDtoTest {
    val venue = Venue(
        UUID.randomUUID(),
        "venue-0",
        address = "Road x at 1324",
        Instant.now().toEpochMilli()
    )

    val dto = NewEventDto(
        "event-x",
        "event-x",
        Instant.now().toEpochMilli(),
        Instant.now().toEpochMilli(),
        1000,
        venue.id
    )

    @Test
    fun shouldCreateAnEventFromDto() {
        val event = NewEventDto.newEvent(
            dto, venue
        )

        assertThat(event).isInstanceOf(Event::class.java)
        assertThat(event.id).isNotNull
        assertThat(event.createdAt).isNotNull
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        val event = NewEventDto.newEvent(
            dto, venue
        )

        assertThat(NewEventDto.fromEntity(event)).isEqualTo(dto)
    }
}