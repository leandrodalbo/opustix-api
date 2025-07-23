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
@Table(name = "ticket_type")
 class TicketType(
    @Id
    val id: UUID,
    val name: String,
    val price: Double,
    val currency: String,

    @Column(name = "sale_start", nullable = false)
    val saleStart: Long,

    @Column(name = "sale_end", nullable = false)
    val saleEnd: Long,

    val quantity: Int,
    val description: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ticketType", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sectors: List<EventSector> = emptyList()
) {

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as TicketType
        return id == other.id
    }

    override fun toString(): String {
        return "TicketType(id=$id, name=$name, description=$description)"
    }

    constructor() : this(
        UUID.randomUUID(),
        "",
        0.0,
        "",
        0L,
        0L,
        0,
        "",
        0L,
        Event()
    )
}
