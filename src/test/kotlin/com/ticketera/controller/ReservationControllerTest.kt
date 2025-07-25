package com.ticketera.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.ticketera.data.PurchaseReservationData
import com.ticketera.data.UserData
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

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
        every { reservationService.newReservations(any<List<NewReservationDto>>(), any()) } returns PurchaseReservationData.purchaseDto
        every { userAuthHeadersService.isAUser(any()) } returns true
        every { userAuthHeadersService.getUser(any()) } returns UserData.user

        val response = mvc.perform(
            post("/ticketera/reservations/new")
                .headers(UserData.httpHeaders)
                .content(objectMapper.writeValueAsString(listOf(PurchaseReservationData.newReservationDto)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { reservationService.newReservations(any<List<NewReservationDto>>(), any()) }
        verify { userAuthHeadersService.isAUser(any()) }
        verify { userAuthHeadersService.getUser(any()) }
    }

    @Test
    fun shouldFetchUserPurchases() {
        every { reservationService.findPurchasesByUser(UserData.user.email) } returns listOf(PurchaseReservationData.purchaseDto)
        every { userAuthHeadersService.isAUser(any()) } returns true
        every { userAuthHeadersService.getUser(any()) } returns UserData.user

        val response = mvc.perform(
            get("/ticketera/reservations/user/purchases")
                .headers(UserData.httpHeaders)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { reservationService.findPurchasesByUser(UserData.user.email) }
        verify { userAuthHeadersService.isAUser(any()) }
        verify { userAuthHeadersService.getUser(any()) }
    }

}