package com.ticketera.controller

import com.ticketera.dto.events.EventDto
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Event
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/ticketera/events")
class EventsController(private val headersService: AuthHeadersService, private val eventsService: EventService) {

    @GetMapping("/all")
    fun findEvents(): List<EventDto> = eventsService.allEvents()

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateEvent(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        updateEventDto: UpdateEventDto
    ): Event {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return eventsService.updateEvent(updateEventDto)
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    fun newEvent(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        newEventDto: NewEventDto
    ): Event {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return eventsService.addEvent(newEventDto)
    }


    @DeleteMapping("/delete/{id}")
    fun deleteEvent(
        @RequestHeader
        headers: Map<String, String>,
        @PathVariable("id")
        id: UUID
    ) {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        eventsService.deleteEvent(id)
    }
}