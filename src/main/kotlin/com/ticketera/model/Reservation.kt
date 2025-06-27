package com.ticketera.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "reservation")
data class Reservation(

    @Id
    private val id: UUID,

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private val purchase: Purchase,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    val ticketType: TicketType,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id", nullable = true)
    val sector: EventSector? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id", nullable = true)
    val seat: EventSeat? = null,

    val price: BigDecimal,
    val status: ReservationStatus,
    val createdAt: Long
)