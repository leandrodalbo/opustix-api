package com.ticketera.dto.events

import com.ticketera.TestData
import com.ticketera.model.Event
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewEventDtoTest : TestData() {

    @Test
    fun shouldCreateAnEventFromDto() {
        val event = NewEventDto.newEvent(
            newEventDto, venue
        )

        assertThat(event).isInstanceOf(Event::class.java)
        assertThat(event.id).isNotNull
        assertThat(event.createdAt).isNotNull
    }

}