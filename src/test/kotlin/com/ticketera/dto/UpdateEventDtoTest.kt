package com.ticketera.dto

import com.ticketera.TestData
import com.ticketera.dto.events.UpdateEventDto
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UpdateEventDtoTest : TestData(){

    @Test
    fun shouldGetAnUpdatedEventFromDto() {
        val updatedEvent = UpdateEventDto.updatedEvent(
            updateEventDto, venue, event
        )
        assertThat(updatedEvent.id).isEqualTo(updateEventDto.id)
        assertThat(updatedEvent.title).isEqualTo(updateEventDto.title)
        assertThat(updatedEvent.venue.id).isEqualTo(updateEventDto.venueId)
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        assertThat(
            UpdateEventDto.fromEntity(event)
        ).isInstanceOf(UpdateEventDto::class.java)
    }
}