package com.ticketera.dto.eventSectors

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSectorDtoTest {

    @Test
    fun shouldCreateDtoFromEntity() {
        val dto = EventSectorDto.fromEntity(TestData.eventSector)
        assertThat(dto).isEqualTo(TestData.eventSectorDto)
    }
}
