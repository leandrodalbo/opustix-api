package com.ticketera.dto

import com.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import java.time.Instant
import java.util.UUID
import kotlin.test.Test

class UpdateVenueDtoTest {
    val venue = Venue(
        UUID.randomUUID(),
        "venue-0",
        address = "Road x at 1324",
        Instant.now().toEpochMilli()
    )

    val dto = UpdateVenueDto(
        venue.id,
        "new-venue-name",
        "new-venue-address"
    )

    @Test
    fun shouldGetAnUpdatedVenueFromDto() {
        val updatedVenue = UpdateVenueDto.updatedVenue(
            dto, venue
        )

        assertThat(updatedVenue.name).isEqualTo(dto.name)
        assertThat(updatedVenue.address).isEqualTo(dto.address)

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