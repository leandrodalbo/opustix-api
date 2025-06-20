package com.ticketera.ticketera.service

import com.ticketera.ticketera.dto.NewVenueDto
import com.ticketera.ticketera.dto.UpdateVenueDto
import com.ticketera.ticketera.exceptions.ErrorMessage
import com.ticketera.ticketera.exceptions.TicketeraException
import com.ticketera.ticketera.model.Event
import com.ticketera.ticketera.model.Venue
import com.ticketera.ticketera.repositories.VenueRepository
import java.time.Instant
import java.util.UUID

class VenueService(
    private val venueRepository: VenueRepository
) {

    fun addVenue(newVenueDto: NewVenueDto) {
        venueRepository.save(
            Venue(
                id = UUID.randomUUID(),
                name = newVenueDto.name,
                address = newVenueDto.address,
                createdAt = Instant.now().toEpochMilli()
            )
        )
    }

    fun updateVenue(updateVenueDto: UpdateVenueDto) {

        val venue = venueRepository.findById(updateVenueDto.id).orElseThrow {
            TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
        }

        venueRepository.save(
            Venue(
                venue.id,
                name = updateVenueDto.name ?: venue.name,
                address = updateVenueDto.address ?: venue.address,
                createdAt = venue.createdAt
            )
        )
    }

    fun deleteVenue(uuid: UUID) = venueRepository.deleteById(uuid)

    fun allVenues() = venueRepository.findAll()
}