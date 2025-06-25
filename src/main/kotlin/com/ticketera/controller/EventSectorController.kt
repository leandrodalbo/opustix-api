package com.ticketera.controller

import com.ticketera.dto.eventSectors.NewEventSectorDto
import com.ticketera.dto.eventSectors.UpdateEventSectorDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.EventSector
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.EventSectorService
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
@RequestMapping("/ticketera/events/sector")
class EventSectorController(
    private val headersService: AuthHeadersService,
    private val eventSectorService: EventSectorService
) {

    @GetMapping("/{eventId}/all")
    fun eventSectors(
        @RequestHeader
        headers: Map<String, String>,
        @PathVariable("eventId")
        eventId: UUID
    ): List<EventSector> {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return eventSectorService.findByEventId(eventId)
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateEventSector(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        updateEventSectorDto: UpdateEventSectorDto
    ): EventSector {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return eventSectorService.updateEventSector(updateEventSectorDto)
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    fun newEventSector(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        newEventSectorDto: NewEventSectorDto
    ): EventSector {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return eventSectorService.addEventSector(newEventSectorDto)
    }

    @DeleteMapping("/delete/{eventId}")
    fun deleteEventSectors(
        @RequestHeader
        headers: Map<String, String>,
        @PathVariable("eventId")
        eventId: UUID
    ) {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        eventSectorService.deleteByEventId(eventId)
    }
}