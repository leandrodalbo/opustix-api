package com.ticketera.dto.venues

import com.ticketera.TestData
import com.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NewVenueDtoTest : TestData() {

    @Test
    fun shouldCreateAVenueFromDto() {
        val venue = NewVenueDto.newVenue(newVenueDto)

        assertThat(venue).isInstanceOf(Venue::class.java)
        assertThat(venue.id).isNotNull
        assertThat(venue.createdAt).isNotNull
    }
}