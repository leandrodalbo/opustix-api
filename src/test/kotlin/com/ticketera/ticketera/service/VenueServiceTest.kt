package com.ticketera.ticketera.service

import com.ticketera.ticketera.dto.NewVenueDto
import com.ticketera.ticketera.dto.UpdateVenueDto
import com.ticketera.ticketera.exceptions.TicketeraException
import com.ticketera.ticketera.model.Venue
import com.ticketera.ticketera.repositories.VenueRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.mockk.just
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.Optional
import java.util.UUID

class VenueServiceTest {

    private val venueRepository: VenueRepository = mockk()

    private val venueService = VenueService(
        venueRepository
    )

    private val venue = Venue(
        UUID.randomUUID(),
        "venue-0",
        address = "Road x at 1324",
        Instant.now().toEpochMilli()
    )


    @Test
    fun shouldSaveAnewVenue() {
        every { venueRepository.save(any()) } returns venue

        val saved = venueService.addVenue(NewVenueDto.fromEntity(venue))

        assertThat(saved).isEqualTo(venue)

        verify { venueRepository.save(any()) }
    }


    @Test
    fun shouldUpdateAVenue() {
        every { venueRepository.findById(any()) } returns Optional.of(venue)
        every { venueRepository.save(any()) } returns venue

        val saved = venueService.updateVenue(UpdateVenueDto.fromEntity(venue))

        assertThat(saved).isEqualTo(venue)

        verify { venueRepository.save(any()) }
        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateNotFoundVenues() {
        every { venueRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                venueService.updateVenue(UpdateVenueDto.fromEntity(venue))
            }

        verify { venueRepository.findById(any()) }
    }


    @Test
    fun shouldDeleteAVenue() {
        every { venueRepository.deleteById(any()) } just runs

        venueService.deleteVenue(venue.id)

        verify { venueRepository.deleteById(any()) }
    }

    @Test
    fun shouldFetchAllEvents() {
        every { venueRepository.findAll() } returns listOf(venue)

        assertThat(venueService.allVenues())
            .isEqualTo(listOf(venue))

        verify { venueRepository.findAll() }
    }
}