package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.TestData
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventSectorService
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

@WebMvcTest(EventSectorController::class)
class EventSectorControllerTest : TestData() {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var eventSectorService: EventSectorService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    val objectMapper = jacksonObjectMapper()

    @Test
    fun shouldFetchEventSectors() {
        every { eventSectorService.findByEventId(any()) } returns listOf(eventSector)

        val response = mvc.perform(
            get("/ticketera/events/sector/${eventId}/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { eventSectorService.findByEventId(any()) }
    }

    @Test
    fun shouldUpdateAnEventSectors() {
        every { eventSectorService.updateEventSector(any()) } returns eventSector
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            put("/ticketera/events/sector/update")
                .headers(httpHeaders)
                .content(objectMapper.writeValueAsString(updateEventSectorDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventSectorService.updateEventSector(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldCreateAnEventSectors() {
        every { eventSectorService.addEventSector(any()) } returns eventSector
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/events/sector/new")
                .headers(httpHeaders)
                .content(objectMapper.writeValueAsString(newEventSectorDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventSectorService.addEventSector(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldDeleteEventSectors() {
        every { eventSectorService.deleteByEventId(any()) } just runs
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            delete("/ticketera/events/sector/delete/${eventId}")
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { eventSectorService.deleteByEventId(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }
}