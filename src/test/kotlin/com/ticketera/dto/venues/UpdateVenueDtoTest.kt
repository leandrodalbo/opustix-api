package com.ticketera.dto.venues

import com.ticketera.TestData
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
}