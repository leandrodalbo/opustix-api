package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.TestData
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.TicketTypeService
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

@WebMvcTest(TicketTypeController::class)
class TicketTypeControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var ticketTypeService: TicketTypeService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    val objectMapper = jacksonObjectMapper()



    @Test
    fun shouldUpdateATicketType() {
        every { ticketTypeService.updateTicketType(any()) } returns TestData.ticketType
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            put("/ticketera/tickets/types/update")
                .headers(TestData.httpHeaders)
                .content(objectMapper.writeValueAsString(TestData.updateTicketTypeDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { ticketTypeService.updateTicketType(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

    @Test
    fun shouldCreateATicketType() {
        every { ticketTypeService.addTicketType(any()) } returns TestData.ticketType
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            post("/ticketera/tickets/types/new")
                .headers(TestData.httpHeaders)
                .content(objectMapper.writeValueAsString(TestData.newTicketTypeDto))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { ticketTypeService.addTicketType(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }

}