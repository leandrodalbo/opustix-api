package com.ticketera.dto.events

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

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
}