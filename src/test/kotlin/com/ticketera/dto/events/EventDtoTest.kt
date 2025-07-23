package com.ticketera.dto.events

import com.ticketera.data.EventData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class EventDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = EventDto.fromEntity(
            EventData.event
        )
        assertThat(dto).isEqualTo(EventData.eventDto)
    }
}