package com.ticketera.dto.eventSectors

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class UpdateEventSectorDtoTest {

    @Test
    fun shouldGetAnUpdatedEventSectorFromDto() {
        val updated = UpdateEventSectorDto.updatedEventSector(
            TestData.updateEventSectorDto, TestData.eventSector, TestData.ticketType
        )

        assertThat(updated.name).isEqualTo(TestData.updateEventSectorDto.name)
        assertThat(updated.description).isEqualTo(TestData.updateEventSectorDto.description)
    }

}