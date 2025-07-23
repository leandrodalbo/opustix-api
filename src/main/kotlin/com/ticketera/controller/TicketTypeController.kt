package com.ticketera.controller

import com.ticketera.dto.ticketTypes.NewTicketTypeDto
import com.ticketera.dto.ticketTypes.UpdateTicketTypeDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.TicketType
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.TicketTypeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ticketera/tickets/types")
class TicketTypeController(
    private val headersService: AuthHeadersService,
    private val ticketTypeService: TicketTypeService
) {

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateTicketType(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        updateTicketTypeDto: UpdateTicketTypeDto
    ): TicketType {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return ticketTypeService.updateTicketType(updateTicketTypeDto)
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    fun newTicketType(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        newTicketTypeDto: NewTicketTypeDto
    ): TicketType {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return ticketTypeService.addTicketType(newTicketTypeDto)
    }
}