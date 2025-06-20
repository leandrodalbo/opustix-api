package com.ticketera.ticketera.dto

import java.util.UUID

data class UpdateVenueDto(
    val id: UUID,
    val name: String?,
    val address: String?
)
