package com.ticketera.ticketera.exceptions

enum class ErrorMessage(val message: String) {
    VENUE_NOT_FOUND("Venue not found."),
    EVENT_NOT_FOUND("Event not found."),
    INVALID_REQUEST("Invalid request."),
    DATABASE_ERROR("A database error occurred."),
    INTERNAL_SERVER_ERROR("Internal server error."),
    TICKET_TYPE_NOT_FOUND("Ticket type not found."),
    SEAT_ALREADY_RESERVED("Seat is already reserved."),
    PURCHASE_FAILED("Purchase failed."),
    RESERVATION_EXPIRED("Reservation has expired.");
}