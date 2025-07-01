package com.ticketera.dto.venues

import com.ticketera.model.Venue
import java.time.Instant
import java.util.UUID

data class NewVenueDto(
    val name: String,
    val address: String,
    val city : String,
    val country: String
) {

    companion object {
        fun fromEntity(venue: Venue): NewVenueDto {
            return NewVenueDto(
                name = venue.name,
                address = venue.address,
                city = venue.city,
                country = venue.country
            )
        }

        fun newVenue(newVenueDto: NewVenueDto): Venue {
            return Venue(
                id = UUID.randomUUID(),
                name = newVenueDto.name,
                address = newVenueDto.address,
                city = newVenueDto.city,
                country = newVenueDto.country,
                createdAt = Instant.now().toEpochMilli()
            )
        }
    }
}