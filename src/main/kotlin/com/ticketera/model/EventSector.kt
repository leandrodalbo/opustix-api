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
@Table(name = "event_sector")
class EventSector(
    @Id
    val id: UUID,

    val name: String,

    val description: String? = null,

    @Column(name = "price_addition")
    val priceAddition: Double? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event
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
        null,
        0L,
        Event()
    )

}
