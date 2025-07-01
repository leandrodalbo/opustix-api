package com.ticketera.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import java.util.UUID

@Entity
@Table(name = "venue")
data class Venue(
    @Id
    val id: UUID,

    val name: String,

    val address: String,

    val city: String,

    val country: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long
) {
    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Venue
        return id == other.id
    }

    override fun toString(): String {
        return "Venue(id=$id, name=$name, createdAt=$createdAt)"
    }

}
