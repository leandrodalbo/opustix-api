package com.ticketera.dto.events

import com.ticketera.data.EventData
import com.ticketera.data.VenueData
import com.ticketera.model.Event
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewEventDtoTest {

    @Test
    fun shouldCreateAnEventFromDto() {
        val event = NewEventDto.newEvent(
            EventData.newEventDto, VenueData.venue
        )

        assertThat(event).isInstanceOf(Event::class.java)
        assertThat(event.id).isNotNull
        assertThat(event.createdAt).isNotNull
    }
}