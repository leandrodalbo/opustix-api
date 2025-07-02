package com.ticketera.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import java.time.Instant
import java.util.UUID


@Entity
@Table(name = "purchase")
class Purchase(

    @Id
    val id: UUID,

    @Column(name = "user_info")
    val userInfo: String,

    @Column(name = "total_price")
    val totalPrice: Double,

    @Column(name = "payment_status")
    @Enumerated(EnumType.ORDINAL)
    val paymentStatus: PaymentStatus,

    @Column(name = "expires_at")
    val expiresAt: Long? = null,

    @Column(name = "created_at")
    val createdAt: Long,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase", cascade = [CascadeType.ALL], orphanRemoval = true)
    val reservations: List<Reservation> = emptyList()

) {
    fun isExpired() = expiresAt?.let { it < Instant.now().toEpochMilli() }

    override fun toString(): String {
        return "Purchase(id=$id, userInfo=$userInfo, totalPrice=$totalPrice, paymentStatus=$paymentStatus, expiresAt=$expiresAt, createdAt=$createdAt)"
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Purchase
        return id == other.id
    }

    constructor() : this(
        UUID.randomUUID(),
        "",
        0.0,
        PaymentStatus.INITIATED,
        null,
        0L,
        emptyList()
    )
}