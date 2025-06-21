package com.ticketera.ticketera.service

import com.ticketera.ticketera.dto.NewEventDto
import com.ticketera.ticketera.dto.NewVenueDto
import com.ticketera.ticketera.dto.UpdateVenueDto
import com.ticketera.ticketera.exceptions.ErrorMessage
import com.ticketera.ticketera.exceptions.TicketeraException
import com.ticketera.ticketera.model.Venue
import com.ticketera.ticketera.repositories.VenueRepository
import java.util.UUID

class VenueService(
    private val venueRepository: VenueRepository
) {

    fun addVenue(newVenueDto: NewVenueDto): Venue {
        return venueRepository.save(
            NewVenueDto.newVenue(newVenueDto)
        )
    }

    fun updateVenue(updateVenueDto: UpdateVenueDto): Venue {

        val venue = venueRepository.findById(updateVenueDto.id).orElseThrow {
            TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
        }

        return venueRepository.save(
            UpdateVenueDto.updatedVenue(updateVenueDto, venue)
        )
    }

    fun deleteVenue(uuid: UUID) = venueRepository.deleteById(uuid)

    fun allVenues() = venueRepository.findAll()
}