package com.ticketera.dto.eventSectors

import com.ticketera.data.EventSectorData
import com.ticketera.data.TicketTypeData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class UpdateEventSectorDtoTest {

    @Test
    fun shouldGetAnUpdatedEventSectorFromDto() {
        val updated = UpdateEventSectorDto.updatedEventSector(
            EventSectorData.updateEventSectorDto, EventSectorData.eventSector, TicketTypeData.ticketType
        )

        assertThat(updated.name).isEqualTo(EventSectorData.updateEventSectorDto.name)
        assertThat(updated.description).isEqualTo(EventSectorData.updateEventSectorDto.description)
    }

}