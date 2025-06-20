package com.ticketera.ticketera.model


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

    @Column(name = "created_at", nullable = false)
    val createdAt: Long
)
