package com.ticketera.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "reservation")
data class Reservation(

    @Id
    val id: UUID,

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    val purchase: Purchase,

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

    val price: Double,

    @Enumerated(EnumType.ORDINAL)
    val status: ReservationStatus,
    val createdAt: Long
) {
    override fun toString(): String {
        return "Reservation(id=$id, status=$status, createdAt=$createdAt)"
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Reservation
        return id == other.id
    }
}