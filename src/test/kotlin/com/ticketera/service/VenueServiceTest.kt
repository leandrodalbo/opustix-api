package com.ticketera.service

import com.ticketera.TestData
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
        every { venueRepository.save(any()) } returns TestData.venue

        val saved = venueService.addVenue(TestData.newVenueDto)

        assertThat(saved).isEqualTo(TestData.venue)

        verify { venueRepository.save(any()) }
    }


    @Test
    fun shouldUpdateAVenue() {
        every { venueRepository.findById(any()) } returns Optional.of(TestData.venue)
        every { venueRepository.save(any()) } returns TestData.venue

        val saved = venueService.updateVenue(TestData.updateVenueDto)

        assertThat(saved).isEqualTo(TestData.venue)

        verify { venueRepository.save(any()) }
        verify { venueRepository.findById(any()) }
    }

    @Test
    fun shouldNotUpdateNotFoundVenues() {
        every { venueRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                venueService.updateVenue(TestData.updateVenueDto)
            }

        verify { venueRepository.findById(any()) }
    }


    @Test
    fun shouldDeleteAVenue() {
        every { venueRepository.deleteById(any()) } just runs

        venueService.deleteVenue(TestData.venue.id)

        verify { venueRepository.deleteById(any()) }
    }

    @Test
    fun shouldFetchAllEvents() {
        every { venueRepository.findAll() } returns listOf(TestData.venue)

        assertThat(venueService.allVenues())
            .isEqualTo(listOf(TestData.venue))

        verify { venueRepository.findAll() }
    }
}