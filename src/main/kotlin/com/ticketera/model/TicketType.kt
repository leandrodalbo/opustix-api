package com.ticketera.model


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "ticket_type")
data class TicketType(
    @Id
    val id: UUID,
    val name: String,
    val price: BigDecimal,
    val currency: String,

    @Column(name = "sale_start", nullable = false)
    val startTime: Long,

    @Column(name = "sale_end", nullable = false)
    val endTime: Long,

    val quantity: Int,
    val description: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: Long,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", nullable = false)
    val event: Event
)
