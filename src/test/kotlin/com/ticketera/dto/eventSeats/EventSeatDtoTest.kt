package com.ticketera.dto.eventSeats

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class EventSeatDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = EventSeatDto.fromEntity(
            TestData.eventSeat
        )
        assertThat(dto).isEqualTo(TestData.eventSeatDto)
    }
}