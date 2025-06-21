package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.dto.NewVenueDto
import com.ticketera.dto.UpdateVenueDto
import com.ticketera.model.Venue
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.VenueService
import io.mockk.every
import io.mockk.verify
import io.mockk.just
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import java.time.Instant
import java.util.UUID

import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders

@WebMvcTest(VenuesController::class)
class VenuesControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var venueService: VenueService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    private val venue =
        Venue(
            UUID.randomUUID(),
            "venue-0",
            address = "Road x at 1324",
            Instant.now().toEpochMilli()
        )


    val headers = HttpHeaders().apply {
        add("X-Roles", "ADMIN,USER")
        add("Authorization", "Bearer token")
    }

    val objectMapper = jacksonObjectMapper()

    @Test
    fun shouldAllVenues() {
        every { venueService.allVenues() } returns listOf(venue)

        val response = mvc.perform(
            get("/ticketera/private/venues/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { venueService.allVenues() }
    }

    @Test
    fun shouldUpdateVenues() {
        every { venueService.updateVenue(any()) } returns venue
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            put("/ticketera/private/venues/update")
                .headers(headers)
                .content(objectMapper.writeValueAsString(UpdateVenueDto.fromEntity(venue)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { venueService.updateVenue(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldCreateVenues() {
        every { venueService.addVenue(any()) } returns venue
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/private/venues/new")
                .headers(headers)
                .content(objectMapper.writeValueAsString(NewVenueDto.fromEntity(venue)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { venueService.addVenue(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldDeleteVenues() {
        every { venueService.deleteVenue(any()) } just runs
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true
        val response = mvc.perform(
            delete("/ticketera/private/venues/delete/${venue.id}")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { venueService.deleteVenue(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }
}