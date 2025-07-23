package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.data.VenueData
import com.ticketera.data.UserData
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

import org.junit.jupiter.api.Test

@WebMvcTest(VenuesController::class)
class VenuesControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var venueService: VenueService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService


    val objectMapper = jacksonObjectMapper()

    @Test
    fun shouldAllVenues() {
        every { venueService.allVenues() } returns listOf(VenueData.venueDto)

        val response = mvc.perform(
            get("/ticketera/venues/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { venueService.allVenues() }
    }

    @Test
    fun shouldUpdateVenues() {
        every { venueService.updateVenue(any()) } returns VenueData.venue
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            put("/ticketera/venues/update")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(VenueData.updateVenueDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { venueService.updateVenue(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldCreateVenues() {
        every { venueService.addVenue(any()) } returns VenueData.venue
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/venues/new")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(VenueData.newVenueDto))
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
            delete("/ticketera/venues/delete/${VenueData.venue.id}")
                .headers(UserData.httpHeaders)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { venueService.deleteVenue(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }
}