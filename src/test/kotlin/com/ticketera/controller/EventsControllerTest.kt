package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.data.EventData
import com.ticketera.data.UserData
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventService
import io.mockk.every
import io.mockk.verify

import org.assertj.core.api.Assertions.assertThat

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

import org.junit.jupiter.api.Test

@WebMvcTest(EventsController::class)
class EventsControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var eventService: EventService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    val objectMapper = jacksonObjectMapper()

    @Test
    fun shouldFetchAllEvents() {
        every { eventService.allEvents() } returns listOf(EventData.eventDto)

        val response = mvc.perform(
            get("/ticketera/events/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { eventService.allEvents() }
    }

    @Test
    fun shouldFetchAnEventDetails() {
        every { eventService.eventDetails(any()) } returns EventData.eventDetailsDto
        every { userAuthHeadersService.isAUser(any()) } returns true

        val response = mvc.perform(
            get("/ticketera/events/${EventData.event.id}/details")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { eventService.eventDetails(any()) }
        verify { userAuthHeadersService.isAUser(any()) }
    }

    @Test
    fun shouldUpdateEvents() {
        every { eventService.updateEvent(any()) } returns EventData.event
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            put("/ticketera/events/update")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(EventData.updateEventDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventService.updateEvent(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldCreateEvents() {
        every { eventService.addEvent(any()) } returns EventData.event
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/events/new")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(EventData.newEventDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventService.addEvent(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

}