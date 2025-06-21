package com.ticketera.ticketera.dto

import com.ticketera.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NewVenueDtoTest {

    val dto = NewVenueDto(
        "new-venue",
        "road x"
    )

    @Test
    fun shouldCreateAVenueFromDto() {
        val venue = NewVenueDto.newVenue(dto)

        assertThat(venue).isInstanceOf(Venue::class.java)
        assertThat(venue.id).isNotNull
        assertThat(venue.createdAt).isNotNull
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        val venue = NewVenueDto.newVenue(dto)

        assertThat(NewVenueDto.fromEntity(venue)).isEqualTo(dto)
    }
}