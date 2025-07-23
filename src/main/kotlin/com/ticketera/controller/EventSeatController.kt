package com.ticketera.controller

import com.ticketera.dto.eventSeats.NewEventSeatsDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.EventSeat
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventSeatService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ticketera/events/seats")
class EventSeatController(
    private val headersService: AuthHeadersService,
    private val eventSeatService: EventSeatService
) {

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    fun newEventSeats(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        newEventSeatsDto: NewEventSeatsDto
    ): List<EventSeat> {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return eventSeatService.generateEventSeats(newEventSeatsDto)
    }
}