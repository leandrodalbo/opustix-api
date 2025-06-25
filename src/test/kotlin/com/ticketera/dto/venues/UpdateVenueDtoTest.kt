package com.ticketera.dto.venues

import com.ticketera.TestData
import org.assertj.core.api.Assertions
import kotlin.test.Test

class UpdateVenueDtoTest: TestData() {

    @Test
    fun shouldGetAnUpdatedVenueFromDto() {
        val updatedVenue = UpdateVenueDto.updatedVenue(
            updateVenueDto, venue
        )

        Assertions.assertThat(updatedVenue.name).isEqualTo(updateVenueDto.name)
        Assertions.assertThat(updatedVenue.address).isEqualTo(updateVenueDto.address)
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        Assertions.assertThat(
            UpdateVenueDto.fromEntity(
                venue
            )
        ).isInstanceOf(UpdateVenueDto::class.java)
    }
}