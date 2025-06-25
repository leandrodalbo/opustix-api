package com.ticketera.dto.eventSectors

import com.ticketera.TestData
import org.assertj.core.api.Assertions
import kotlin.test.Test

class UpdateEventSectorDtoTest : TestData() {

    @Test
    fun shouldGetAnUpdatedEventSectorFromDto() {
        val updated = UpdateEventSectorDto.updatedEventSector(
            updateEventSectorDto, eventSector, event
        )

        Assertions.assertThat(updated.name).isEqualTo(updateEventSectorDto.name)
        Assertions.assertThat(updated.description).isEqualTo(updateEventSectorDto.description)
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        Assertions.assertThat(
            UpdateEventSectorDto.fromEntity(
                eventSector
            )
        ).isInstanceOf(UpdateEventSectorDto::class.java)
    }
}