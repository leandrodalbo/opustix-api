package com.ticketera.dto.venues

import com.ticketera.data.VenueData
import com.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NewVenueDtoTest {

    @Test
    fun shouldCreateAVenueFromDto() {
        val venue = NewVenueDto.newVenue(VenueData.newVenueDto)

        assertThat(venue).isInstanceOf(Venue::class.java)
        assertThat(venue.id).isNotNull
        assertThat(venue.createdAt).isNotNull
    }
}