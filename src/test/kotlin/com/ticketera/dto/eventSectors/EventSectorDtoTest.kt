package com.ticketera.dto.eventSectors

import com.ticketera.data.EventSectorData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSectorDtoTest {

    @Test
    fun shouldCreateDtoFromEntity() {
        val dto = EventSectorDto.fromEntity(EventSectorData.eventSector)
        assertThat(dto).isEqualTo(EventSectorData.eventSectorDto)
    }
}
