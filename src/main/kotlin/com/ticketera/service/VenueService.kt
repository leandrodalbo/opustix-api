package com.ticketera.service

import com.ticketera.dto.venues.NewVenueDto
import com.ticketera.dto.venues.UpdateVenueDto
import com.ticketera.dto.venues.VenueDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Venue
import com.ticketera.repositories.VenueRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class VenueService(
    private val venueRepository: VenueRepository
) {

    @Transactional
    fun addVenue(newVenueDto: NewVenueDto): Venue {
        return venueRepository.save(
            NewVenueDto.newVenue(newVenueDto)
        )
    }

    @Transactional
    fun updateVenue(updateVenueDto: UpdateVenueDto): Venue {

        val venue = venueRepository.findById(updateVenueDto.id).orElseThrow {
            TicketeraException(ErrorMessage.VENUE_NOT_FOUND)
        }

        return venueRepository.save(
            UpdateVenueDto.updatedVenue(updateVenueDto, venue)
        )
    }

    @Transactional
    fun deleteVenue(uuid: UUID) = venueRepository.deleteById(uuid)

    fun allVenues() = venueRepository.findAll().map { VenueDto.fromEntity(it) }
}