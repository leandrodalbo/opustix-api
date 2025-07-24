package com.ticketera.dto.eventSectors

import com.ticketera.data.EventSectorData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSectorDtoTest {

    @Test
    fun shouldCreateDtoFromEntity() {
        val dto = EventSectorDto.fromEntities(EventSectorData.eventSector, EventSectorData.eventSector.ticketType)
        assertThat(dto).isEqualTo(EventSectorData.eventSectorDto)
    }
}
