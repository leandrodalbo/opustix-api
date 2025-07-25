package com.ticketera.exceptions

enum class ErrorMessage(val message: String) {
    VENUE_NOT_FOUND("Venue not found."),
    EVENT_NOT_FOUND("Event not found."),
    BANNER_NOT_FOUND("Banner not found."),
    INVALID_REQUEST("Invalid request."),
    DATABASE_ERROR("A database error occurred."),
    INTERNAL_SERVER_ERROR("Internal server error."),
    TICKET_TYPE_NOT_FOUND("Ticket type not found."),
    TICKET_TYPE_NOT_AVAILABLE("Ticket type is not available."),
    EVENT_SECTOR_NOT_FOUND("Event Sector not found."),
    EVENT_SEAT_NOT_FOUND("Event Seat not found."),
    ALREADY_RESERVED("Already reserved."),
    PURCHASE_FAILED("Purchase failed."),
    RESERVATION_EXPIRED("Reservation has expired.");
}