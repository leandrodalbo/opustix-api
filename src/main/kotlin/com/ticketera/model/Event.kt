package com.ticketera.model


import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import java.time.Instant
import java.time.temporal.ChronoUnit
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

    val category: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", nullable = false)
    val venue: Venue,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
    val banners: List<Banner> = emptyList()
) {

    fun hasFinished() = Instant.now().minus(1, ChronoUnit.DAYS).toEpochMilli() > endTime

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
