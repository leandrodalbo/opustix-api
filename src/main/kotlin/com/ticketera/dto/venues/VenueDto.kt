package com.ticketera.dto.venues

import com.ticketera.model.Venue
import java.util.UUID

data class VenueDto(
    val id: UUID,
    val name: String,
    val address: String,
    val city: String,
    val country: String,
) {
    companion object {
        fun fromEntity(venue: Venue) =
            VenueDto(venue.id, venue.name, venue.address, venue.city, venue.country)
    }
}