package com.ticketera.dto.events

import com.ticketera.dto.banners.BannerDto
import com.ticketera.dto.venues.VenueDto
import com.ticketera.model.Event

import java.util.UUID

data class EventDto(
    val id: UUID,
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val capacity: Int,
    val category: String,
    val venue: VenueDto,
    val banners: List<BannerDto> = emptyList()
) {
    companion object {
        fun fromEntity(event: Event) = EventDto(
            event.id,
            event.title,
            event.description,
            event.startTime,
            event.endTime,
            event.capacity,
            event.category,
            VenueDto.fromEntity(event.venue),
            event.banners.map { BannerDto.fromEntity(it) }
        )
    }
}