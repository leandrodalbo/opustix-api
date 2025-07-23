package com.ticketera.service

import com.ticketera.data.VenueData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.VenueRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import io.mockk.just
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class VenueServiceTest {

    private val venueRepository: VenueRepository = mockk()

    private val venueService = VenueService(
        venueRepository
    )


    @Test
    fun shouldSaveAnewVenue() {
        every { venueRepository.save(any()) } returns VenueData.venue

        val saved = venueService.addVenue(VenueData.newVenueDto)

        assertThat(saved).isEqualTo(VenueData.venue)

        verify { venueRepository.save(any()) }
    }


    @Test
    fun shouldUpdateAVenue() {
        every { venueRepository.findById(any()) } returns Optional.of(VenueData.venue)
        every { venueRepository.save(any()) } returns VenueData.venue

        val saved = venueService.updateVenue(VenueData.updateVenueDto)

        assertThat(saved).isEqualTo(VenueData.venue)

        verify { venueRepository.save(any()) }
        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateNotFoundVenues() {
        every { venueRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                venueService.updateVenue(VenueData.updateVenueDto)
            }

        verify { venueRepository.findById(any()) }
    }


    @Test
    fun shouldDeleteAVenue() {
        every { venueRepository.deleteById(any()) } just runs

        venueService.deleteVenue(VenueData.venue.id)

        verify { venueRepository.deleteById(any()) }
    }

    @Test
    fun shouldFetchAllEvents() {
        every { venueRepository.findAll() } returns listOf(VenueData.venue)

        assertThat(venueService.allVenues())
            .isEqualTo(listOf(VenueData.venueDto))

        verify { venueRepository.findAll() }
    }
}