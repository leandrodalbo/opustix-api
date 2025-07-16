package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.TestData
import com.ticketera.dto.reservation.NewReservationDto
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.ReservationService
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

@WebMvcTest(ReservationController::class)
class ReservationControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var reservationService: ReservationService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    val objectMapper = jacksonObjectMapper()

    @Test
    fun shouldCreateReservations() {
        every { reservationService.newReservations(any<List<NewReservationDto>>(), any()) } returns TestData.purchaseDto
        every { userAuthHeadersService.isAUser(any()) } returns true
        every { userAuthHeadersService.getUser(any()) } returns TestData.user

        val response = mvc.perform(
            post("/ticketera/reservations/new")
                .headers(TestData.httpHeaders)
                .content(objectMapper.writeValueAsString(listOf(TestData.newReservationDto)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { reservationService.newReservations(any<List<NewReservationDto>>(), any()) }
        verify { userAuthHeadersService.isAUser(any()) }
        verify { userAuthHeadersService.getUser(any()) }
    }
}