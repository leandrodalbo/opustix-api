package com.ticketera.dto.events

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class EventDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = EventDto.fromEntity(
            TestData.event
        )
        assertThat(dto).isEqualTo(TestData.eventDto)

    }
}