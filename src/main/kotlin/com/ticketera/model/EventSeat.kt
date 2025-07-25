package com.ticketera.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import java.util.UUID

@Entity
@Table(name = "event_seat")
class EventSeat(
    @Id
    val id: UUID,
    val label: String,

    @Column(name = "seat_number")
    val seatNumber: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id", nullable = false)
    val sector: EventSector
) {


    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EventSeat
        return id == other.id
    }

    override fun toString(): String {
        return "EventSeat(id=$id, label=$label)"
    }

    constructor() : this(
        UUID.randomUUID(),
        "",
        null,
        0L,
        EventSector()
    )
}
