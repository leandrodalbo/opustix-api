package com.ticketera.dto

import com.ticketera.model.Venue
import java.time.Instant
import java.util.UUID

data class NewVenueDto(
    val name: String,
    val address: String,
) {

    companion object {
        fun fromEntity(venue: Venue): NewVenueDto {
            return NewVenueDto(
                name = venue.name,
                address = venue.address,
            )
        }

        fun newVenue(newVenueDto: NewVenueDto): Venue {
            return Venue(
                id = UUID.randomUUID(),
                name = newVenueDto.name,
                address = newVenueDto.address,
                createdAt = Instant.now().toEpochMilli()
            )
        }
    }
}