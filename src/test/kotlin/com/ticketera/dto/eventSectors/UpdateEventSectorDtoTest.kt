package com.ticketera.dto.eventSectors

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class UpdateEventSectorDtoTest : TestData() {

    @Test
    fun shouldGetAnUpdatedEventSectorFromDto() {
        val updated = UpdateEventSectorDto.updatedEventSector(
            updateEventSectorDto, eventSector, event
        )

        assertThat(updated.name).isEqualTo(updateEventSectorDto.name)
        assertThat(updated.description).isEqualTo(updateEventSectorDto.description)
    }

}