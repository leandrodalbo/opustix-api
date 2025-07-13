package com.ticketera.controller

import com.ticketera.dto.venues.NewVenueDto
import com.ticketera.dto.venues.UpdateVenueDto
import com.ticketera.dto.venues.VenueDto
import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Venue
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.VenueService
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
@RequestMapping("/ticketera/venues")
class VenuesController(private val headersService: AuthHeadersService, private val venueService: VenueService) {

    @GetMapping("/all")
    fun venues(): List<VenueDto> = venueService.allVenues()

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateVenue(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        updateVenueDto: UpdateVenueDto
    ): Venue {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return venueService.updateVenue(updateVenueDto)
    }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    fun newVenue(
        @RequestHeader
        headers: Map<String, String>,
        @RequestBody
        newVenueDto: NewVenueDto
    ): Venue {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        return venueService.addVenue(newVenueDto)
    }


    @DeleteMapping("/delete/{id}")
    fun deleteVenue(
        @RequestHeader
        headers: Map<String, String>,
        @PathVariable("id")
        id: UUID
    ) {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        venueService.deleteVenue(id)
    }
}