package com.ticketera.dto.events

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class EventDetailsDtoTest {


    @Test
    fun shouldCreateEventDetailsDtoFromEntities() {
        val dto = EventDetailsDto.fromEntities(
            event = TestData.event,
            venue = TestData.venue,
            ticketType = listOf(TestData.ticketType),
            sector = listOf(TestData.eventSector),
            seat = listOf(TestData.eventSeat)
        )

        assertThat(dto).isEqualTo(TestData.eventDetailsDto)

    }
}