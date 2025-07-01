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
@Table(name = "event_banners")
data class Banner(
    @Id
    val id: UUID,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @Column(name = "is_main")
    val isMain: Boolean = false,

    @Column(name = "is_second")
    val isSecond: Boolean = false,

    @Column(name = "is_additional")
    val isAdditional: Boolean = false,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event,

) {

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Banner
        return id == other.id
    }

    override fun toString(): String {
        return "Banner(id=$id, url=$imageUrl)"
    }
}
