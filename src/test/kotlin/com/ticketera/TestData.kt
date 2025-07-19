package com.ticketera

import com.ticketera.dto.banners.BannerDto
import com.ticketera.dto.eventSeats.EventSeatDto
import com.ticketera.dto.eventSeats.NewEventSeatsDto
import com.ticketera.dto.eventSectors.EventSectorDto
import com.ticketera.dto.eventSectors.NewEventSectorDto
import com.ticketera.dto.eventSectors.UpdateEventSectorDto
import com.ticketera.dto.events.EventDto
import com.ticketera.dto.events.NewEventDto
import com.ticketera.dto.events.UpdateEventDto
import com.ticketera.dto.purchase.PurchaseDto
import com.ticketera.dto.reservation.NewReservationDto
import com.ticketera.dto.reservation.ReservationDto
import com.ticketera.dto.ticketTypes.NewTicketTypeDto
import com.ticketera.dto.ticketTypes.TicketTypeDto
import com.ticketera.dto.ticketTypes.UpdateTicketTypeDto
import com.ticketera.dto.venues.NewVenueDto
import com.ticketera.dto.venues.UpdateVenueDto
import com.ticketera.dto.venues.VenueDto
import com.ticketera.model.Banner
import com.ticketera.model.Event
import com.ticketera.model.EventSeat
import com.ticketera.model.EventSector
import com.ticketera.model.PaymentStatus
import com.ticketera.model.Purchase
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import com.ticketera.model.TicketType
import com.ticketera.model.User
import com.ticketera.model.UserRole
import com.ticketera.model.Venue
import org.springframework.http.HttpHeaders
import org.springframework.mock.web.MockMultipartFile
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID

abstract class TestData {
    companion object {

        val headersMap = mapOf(
            "x-Roles" to "USER,X-USER-ROLE,ADMIN",
            "X-name" to "any-name",
            "x-email" to "any@mail.com",
        )

        val httpHeaders = HttpHeaders().apply {
            add("X-Roles", "ADMIN,USER")
            add("Authorization", "Bearer token")
        }

        private val imageBytes = javaClass.getResourceAsStream("/banner.jpg").readBytes()
        val multipartFile = MockMultipartFile("file", "test-image.jpg", "image/jpeg", imageBytes)

        val user = User(
            "any-name",
            "family-name",
            "any@mail.com",
            setOf(UserRole.USER)
        )

        val venue = Venue(
            UUID.randomUUID(),
            "venue-0",
            address = "Road x at 1324",
            city = "CABA",
            country = "Argentina",
            Instant.now().toEpochMilli()
        )

        val event = Event(
            UUID.randomUUID(),
            "event-x",
            "event-x",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            1000,
            "concert",
            venue,

            Instant.now().toEpochMilli()
        )

        val banner = Banner(
            UUID.randomUUID(),
            "https://s3.banner.img?id=${UUID.randomUUID()}",
            true,
            false,
            false,
            Instant.now().toEpochMilli(),
            event
        )

        val ticketType = TicketType(
            UUID.randomUUID(),
            "GOLDEN",
            132.44,
            "ARS",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            500,
            "Golden Ticket",
            Instant.now().toEpochMilli(),
            event
        )

        val eventSector = EventSector(
            UUID.randomUUID(),
            "Testing Event Sector",
            "Testing Event Sector",
            10.0,
            Instant.now().toEpochMilli(),
            event
        )

        val eventSeat = EventSeat(
            UUID.randomUUID(),
            "Testing Seat",
            "Seat on Row X",
            "10",
            0.0,
            Instant.now().toEpochMilli(),
            event,
            eventSector
        )

        val purchase = Purchase(
            UUID.randomUUID(),
            "user data",
            100.0,
            PaymentStatus.INITIATED,
            Instant.now().plus(10, ChronoUnit.MINUTES).toEpochMilli(),
            Instant.now().toEpochMilli(),
        )

        val reservation = Reservation(
            UUID.randomUUID(),
            purchase,
            event,
            ticketType,
            eventSector,
            eventSeat,
            100.0,
            ReservationStatus.PENDING,
            Instant.now().toEpochMilli()
        )

        val newVenueDto = NewVenueDto(
            "new-venue",
            "road x",
            "CABA",
            "Argentina",
        )

        val ticketTypeDto = TicketTypeDto(
            ticketType.id,
            ticketType.name,
            ticketType.price,
            ticketType.currency,
            ticketType.saleStart,
            ticketType.saleEnd,
            ticketType.description
        )

        val updateVenueDto = UpdateVenueDto(
            venue.id,
            "new-venue-name",
            "new-venue-address",
            "CABA",
            "Argentina",
        )

        val newEventDto = NewEventDto(
            "event-x",
            "event-x",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            1000,
            "concert",
            venue.id
        )

        val eventDto = EventDto.fromEntity(event)
        val venueDto = VenueDto.fromEntity(venue)
        val bannerDto = BannerDto.fromEntity(banner)
        val eventSectorDto = EventSectorDto.fromEntity(eventSector)
        val eventSeatDto = EventSeatDto.fromEntity(eventSeat)

        val updateEventDto = UpdateEventDto(
            id = event.id,
            "event-new-title",
            "event-updated",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            1000,
            "concert",
            venue.id
        )

        val newTicketTypeDto = NewTicketTypeDto(
            "GOLDEN",
            150.0,
            "ARS",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            500,
            "golden-ticket",
            event.id
        )

        val updateTicketTypeDto = UpdateTicketTypeDto(
            ticketType.id,
            "GOLDEN",
            150.0,
            "ARS",
            Instant.now().toEpochMilli(),
            Instant.now().toEpochMilli(),
            500,
            "golden-ticket",
            event.id
        )

        val newEventSectorDto = NewEventSectorDto(
            "sector-1",
            "test-sector",
            11.11,
            event.id
        )

        val updateEventSectorDto = UpdateEventSectorDto(
            eventSector.id,
            "sector-1-new-name",
            "updated-testing-sector",
            11.11,
            event.id
        )

        val newEventSeatsDto = NewEventSeatsDto(
            1,
            5,
            "row-0",
            "row-0",
            0.0,
            event.id,
            eventSector.id
        )

        val newReservationDto = NewReservationDto(
            event.id,
            ticketType.id,
            null,
            null
        )

        val reservationDto = ReservationDto(
            reservation.id,
            reservation.event.id,
            reservation.ticketType.id,
            reservation.ticketType.name,
            reservation.ticketType.price,
            reservation.ticketType.currency,
            reservation.price,
            reservation.status,
            reservation.sector?.id,
            reservation.sector?.name,
            reservation.seat?.id,
            reservation.seat?.label,
            reservation.seat?.seatRowInfo
        )

        val purchaseDto = PurchaseDto(
            purchase.id,
            purchase.userInfo,
            purchase.totalPrice,
            purchase.paymentStatus,
            purchase.expiresAt,
            listOf(reservationDto)
        )
    }
}