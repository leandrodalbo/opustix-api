package com.ticketera.dto

import com.ticketera.TestData
import com.ticketera.dto.events.NewEventDto
import com.ticketera.model.Event
import org.assertj.core.api.Assertions.assertThat

import kotlin.test.Test

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

    @Test
    fun shouldGetDtoFromTheEntity() {
        val event = NewEventDto.newEvent(
            newEventDto, venue
        )

        assertThat(NewEventDto.fromEntity(event)).isEqualTo(newEventDto)
    }
}