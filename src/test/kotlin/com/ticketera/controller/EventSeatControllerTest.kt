package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.data.EventSeatData
import com.ticketera.data.UserData
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventSeatService
import io.mockk.every
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
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
    fun shouldCreateEventSeats() {
        every { eventSeatService.generateEventSeats(any()) } returns listOf(EventSeatData.eventSeat)
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/events/seats/new")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(EventSeatData.newEventSeatsDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { eventSeatService.generateEventSeats(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

}