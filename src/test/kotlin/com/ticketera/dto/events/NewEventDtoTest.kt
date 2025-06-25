package com.ticketera.dto.events

import com.ticketera.TestData
import com.ticketera.model.Event
import org.assertj.core.api.Assertions
import kotlin.test.Test

class NewEventDtoTest : TestData() {

    @Test
    fun shouldCreateAnEventFromDto() {
        val event = NewEventDto.newEvent(
            newEventDto, venue
        )

        Assertions.assertThat(event).isInstanceOf(Event::class.java)
        Assertions.assertThat(event.id).isNotNull
        Assertions.assertThat(event.createdAt).isNotNull
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        Assertions.assertThat(NewEventDto.fromEntity(event))
            .isInstanceOf(NewEventDto::class.java)
    }
}