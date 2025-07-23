package com.ticketera.dto.eventSeats

import com.ticketera.data.EventSeatData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class EventSeatDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = EventSeatDto.fromEntity(
            EventSeatData.eventSeat
        )
        assertThat(dto).isEqualTo(EventSeatData.eventSeatDto)
    }
}