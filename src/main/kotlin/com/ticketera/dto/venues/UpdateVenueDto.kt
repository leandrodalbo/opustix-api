package com.ticketera.dto.venues

import com.ticketera.model.Venue
import java.util.UUID

data class UpdateVenueDto(
    val id: UUID,
    val name: String?,
    val address: String?,
    val city: String?,
    val country: String?
) {
    companion object {
        fun fromEntity(venue: Venue): UpdateVenueDto {
            return UpdateVenueDto(
                id = venue.id,
                name = venue.name,
                address = venue.address,
                city = venue.city,
                country = venue.country
            )
        }

        fun updatedVenue(updateVenueDto: UpdateVenueDto, venue: Venue): Venue {
            return Venue(
                venue.id,
                name = updateVenueDto.name ?: venue.name,
                address = updateVenueDto.address ?: venue.address,
                city = updateVenueDto.city ?: venue.city,
                country = updateVenueDto.country ?:venue.country,
                createdAt = venue.createdAt
            )
        }
    }
}