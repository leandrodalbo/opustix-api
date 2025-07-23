package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.data.UserData
import com.ticketera.data.EventSectorData
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventSectorService
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

import org.junit.jupiter.api.Test

@WebMvcTest(EventSectorController::class)
class EventSectorControllerTest  {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var eventSectorService: EventSectorService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    val objectMapper = jacksonObjectMapper()


    @Test
    fun shouldUpdateAnEventSectors() {
        every { eventSectorService.updateEventSector(any()) } returns EventSectorData.eventSector
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            put("/ticketera/events/sector/update")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(EventSectorData.updateEventSectorDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventSectorService.updateEventSector(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldCreateAnEventSectors() {
        every { eventSectorService.addEventSector(any()) } returns EventSectorData.eventSector
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/events/sector/new")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(EventSectorData.newEventSectorDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventSectorService.addEventSector(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

}