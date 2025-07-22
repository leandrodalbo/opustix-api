package com.ticketera.dto.eventSectors

import com.ticketera.model.EventSector
import java.util.UUID

data class EventSectorDto(
    val id: UUID,
    val name: String,
    val description: String?,
    val priceAddition: Double?,
) {
    companion object {
        fun fromEntity(sector: EventSector) = EventSectorDto(
            id = sector.id,
            name = sector.name,
            description = sector.description,
            priceAddition = sector.priceAddition,
        )
    }
}
