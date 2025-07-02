package com.ticketera.dto.venues

import com.ticketera.TestData
import com.ticketera.model.Venue
import org.assertj.core.api.Assertions
import kotlin.test.Test

class NewVenueDtoTest : TestData() {

    @Test
    fun shouldCreateAVenueFromDto() {
        val venue = NewVenueDto.newVenue(newVenueDto)

        Assertions.assertThat(venue).isInstanceOf(Venue::class.java)
        Assertions.assertThat(venue.id).isNotNull
        Assertions.assertThat(venue.createdAt).isNotNull
    }
}