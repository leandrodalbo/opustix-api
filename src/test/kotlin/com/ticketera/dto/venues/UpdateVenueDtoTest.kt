package com.ticketera.dto.venues

import com.ticketera.data.VenueData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UpdateVenueDtoTest {

    @Test
    fun shouldGetAnUpdatedVenueFromDto() {
        val updatedVenue = UpdateVenueDto.updatedVenue(
            VenueData.updateVenueDto, VenueData.venue
        )

        assertThat(updatedVenue.name).isEqualTo(VenueData.updateVenueDto.name)
        assertThat(updatedVenue.address).isEqualTo(VenueData.updateVenueDto.address)
    }
}