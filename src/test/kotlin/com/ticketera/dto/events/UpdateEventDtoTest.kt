package com.ticketera.dto.events

import com.ticketera.data.EventData
import com.ticketera.data.VenueData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class UpdateEventDtoTest {

    @Test
    fun shouldGetAnUpdatedEventFromDto() {
        val updatedEvent = UpdateEventDto.updatedEvent(
            EventData.updateEventDto, VenueData.venue, EventData.event
        )
        assertThat(updatedEvent.id).isEqualTo(EventData.updateEventDto.id)
        assertThat(updatedEvent.title).isEqualTo(EventData.updateEventDto.title)
        assertThat(updatedEvent.venue.id).isEqualTo(EventData.updateEventDto.venueId)
    }
}