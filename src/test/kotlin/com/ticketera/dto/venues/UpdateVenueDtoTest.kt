package com.ticketera.dto.venues

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UpdateVenueDtoTest {

    @Test
    fun shouldGetAnUpdatedVenueFromDto() {
        val updatedVenue = UpdateVenueDto.updatedVenue(
            TestData.updateVenueDto, TestData.venue
        )

        assertThat(updatedVenue.name).isEqualTo(TestData.updateVenueDto.name)
        assertThat(updatedVenue.address).isEqualTo(TestData.updateVenueDto.address)
    }
}