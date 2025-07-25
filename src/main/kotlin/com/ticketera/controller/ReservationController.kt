package com.ticketera.controller

import com.ticketera.dto.purchase.PurchaseDto
import com.ticketera.dto.reservation.NewReservationDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.ReservationService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ticketera/reservations")
class ReservationController(
    private val headersService: AuthHeadersService,
    private val reservationService: ReservationService
) {

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    fun newTicketType(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        reservations: List<NewReservationDto>
    ): PurchaseDto {
        if (!headersService.isAUser(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)

        val user = headersService.getUser(headers)

        return reservationService.newReservations(reservations, user.email)
    }

    @GetMapping("/user/purchases")
    @ResponseStatus(HttpStatus.OK)
    fun getUserPurchases(
        @RequestHeader
        headers: Map<String, String>
    ): List<PurchaseDto> {
        if (!headersService.isAUser(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)

        val user = headersService.getUser(headers)

        return reservationService.findPurchasesByUser(user.email)
    }
}