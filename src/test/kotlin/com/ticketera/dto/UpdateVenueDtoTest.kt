package com.ticketera.dto

import com.ticketera.TestData
import com.ticketera.dto.venues.UpdateVenueDto
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UpdateVenueDtoTest: TestData() {

    @Test
    fun shouldGetAnUpdatedVenueFromDto() {
        val updatedVenue = UpdateVenueDto.updatedVenue(
            updateVenueDto, venue
        )

        assertThat(updatedVenue.name).isEqualTo(updateVenueDto.name)
        assertThat(updatedVenue.address).isEqualTo(updateVenueDto.address)
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        assertThat(
            UpdateVenueDto.fromEntity(
                venue
            )
        ).isInstanceOf(UpdateVenueDto::class.java)
    }
}