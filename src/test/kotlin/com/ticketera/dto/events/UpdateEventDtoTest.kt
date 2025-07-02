package com.ticketera.dto.events

import com.ticketera.TestData
import org.assertj.core.api.Assertions
import kotlin.test.Test

class UpdateEventDtoTest : TestData(){

    @Test
    fun shouldGetAnUpdatedEventFromDto() {
        val updatedEvent = UpdateEventDto.updatedEvent(
            updateEventDto, venue, event
        )
        Assertions.assertThat(updatedEvent.id).isEqualTo(updateEventDto.id)
        Assertions.assertThat(updatedEvent.title).isEqualTo(updateEventDto.title)
        Assertions.assertThat(updatedEvent.venue.id).isEqualTo(updateEventDto.venueId)
    }
}