package com.ticketera.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity
@Table(name = "event_sector")
class EventSector(
    @Id
    val id: UUID,

    val name: String,

    val description: String? = null,


    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_type_id", nullable = false)
    val ticketType: TicketType,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sector", cascade = [CascadeType.ALL], orphanRemoval = true)
    val seats: List<EventSeat> = emptyList()
) {


    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EventSector
        return id == other.id
    }

    override fun toString(): String {
        return "EventSector(id=$id, name=$name, description=$description)"
    }

    constructor() : this(
        UUID.randomUUID(),
        "",
        null,
        0L,
        TicketType()
    )

}
