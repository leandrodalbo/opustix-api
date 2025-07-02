package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.TestData
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventSeatService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete

import org.junit.jupiter.api.Test

@WebMvcTest(EventSeatController::class)
class EventSeatControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var eventSeatService: EventSeatService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    val objectMapper = jacksonObjectMapper()

    @Test
    fun shouldFetchEventSeats() {
        every { eventSeatService.findSeats(any(), any()) } returns listOf(TestData.eventSeat)

        val response = mvc.perform(
            get("/ticketera/events/seats/${TestData.event.id}/all")
                .param("sectorId", TestData.eventSector.id.toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { eventSeatService.findSeats(any(), any()) }
    }


    @Test
    fun shouldCreateEventSeats() {
        every { eventSeatService.generateEventSeats(any()) } returns listOf(TestData.eventSeat)
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/events/seats/new")
                .headers(TestData.httpHeaders)
                .content(objectMapper.writeValueAsString(TestData.newEventSeatsDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventSeatService.generateEventSeats(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldDeleteEventSeats() {
        every { eventSeatService.deleteSeats(any(), any()) } just runs
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            delete("/ticketera/events/seats/delete/${TestData.event.id}")
                .headers(TestData.httpHeaders)
                .param("sectorId", TestData.eventSector.id.toString())
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { eventSeatService.deleteSeats(any(), any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }
}