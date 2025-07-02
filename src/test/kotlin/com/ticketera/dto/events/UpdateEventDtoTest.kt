package com.ticketera.dto.events

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class UpdateEventDtoTest {

    @Test
    fun shouldGetAnUpdatedEventFromDto() {
        val updatedEvent = UpdateEventDto.updatedEvent(
            TestData.updateEventDto, TestData.venue, TestData.event
        )
        assertThat(updatedEvent.id).isEqualTo(TestData.updateEventDto.id)
        assertThat(updatedEvent.title).isEqualTo(TestData.updateEventDto.title)
        assertThat(updatedEvent.venue.id).isEqualTo(TestData.updateEventDto.venueId)
    }
}