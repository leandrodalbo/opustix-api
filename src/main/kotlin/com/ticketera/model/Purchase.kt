package com.ticketera.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.CascadeType
import jakarta.persistence.FetchType
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID


@Entity
@Table(name = "purchase")
data class Purchase(

    @Id
    val id: UUID,

    @Column(name = "user_info")
    val userInfo: String,

    @Column(name = "total_price")
    val totalPrice: BigDecimal,

    @Column(name = "payment_status")
    val paymentStatus: PaymentStatus,

    @Column(name = "expires_at")
    val expiresAt: Long? = null,

    @Column(name = "created_at")
    val createdAt: Long,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchase", cascade = [CascadeType.ALL], orphanRemoval = true)
    val reservations: MutableList<Reservation?> = ArrayList<Reservation?>()

) {
    fun isExpired() = expiresAt?.let { it < Instant.now().toEpochMilli() }
}