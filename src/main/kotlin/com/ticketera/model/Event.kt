package com.ticketera.model


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import java.util.UUID

@Entity
@Table(name = "event")
data class Event(
    @Id
    val id: UUID,

    val title: String,

    val description: String,

    @Column(name = "start_time", nullable = false)
    val startTime: Long,

    @Column(name = "end_time", nullable = false)
    val endTime: Long,

    val capacity: Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", nullable = false)
    val venue: Venue,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long
) {

    override fun toString(): String {
        return "Event(id=$id, title=$title, description=$description)"
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Event
        return id == other.id
    }
}
