package com.ticketera.data

import com.ticketera.dto.venues.NewVenueDto
import com.ticketera.dto.venues.UpdateVenueDto
import com.ticketera.dto.venues.VenueDto
import com.ticketera.model.Venue
import java.time.Instant
import java.util.UUID

class VenueData {
    companion object {

        val venue = Venue(
            UUID.randomUUID(),
            "venue-0",
            address = "Road x at 1324",
            city = "CABA",
            country = "Argentina",
            Instant.now().toEpochMilli()
        )

        val newVenueDto = NewVenueDto(
            "new-venue",
            "road x",
            "CABA",
            "Argentina",
        )


        val updateVenueDto = UpdateVenueDto(
            venue.id,
            "new-venue-name",
            "new-venue-address",
            "CABA",
            "Argentina",
        )

        val venueDto = VenueDto.Companion.fromEntity(venue)
    }
}