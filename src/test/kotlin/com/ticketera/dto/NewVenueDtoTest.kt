package com.ticketera.dto

import com.ticketera.TestData
import com.ticketera.dto.venues.NewVenueDto
import com.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NewVenueDtoTest : TestData(){

    @Test
    fun shouldCreateAVenueFromDto() {
        val venue = NewVenueDto.newVenue(newVenueDto)

        assertThat(venue).isInstanceOf(Venue::class.java)
        assertThat(venue.id).isNotNull
        assertThat(venue.createdAt).isNotNull
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        val venue = NewVenueDto.newVenue(newVenueDto)

        assertThat(NewVenueDto.fromEntity(venue)).isEqualTo(newVenueDto)
    }
}