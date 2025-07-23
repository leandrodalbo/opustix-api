package com.ticketera.dto.events

import com.ticketera.data.BannerData
import com.ticketera.data.EventData
import com.ticketera.data.VenueData
import com.ticketera.data.TicketTypeData
import com.ticketera.data.EventSectorData
import com.ticketera.data.EventSeatData

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class EventDetailsDtoTest {

    @Test
    fun shouldCreateEventDetailsDtoFromEntities() {
        val dto = EventDetailsDto.fromEntities(
            event = EventData.event,
            venue = VenueData.venue,
            BannerData.bannerImageUrl,
            ticketType = listOf(TicketTypeData.ticketType),
            sector = setOf(EventSectorData.eventSector),
            seat = setOf(EventSeatData.eventSeat)
        )
        assertThat(dto).isEqualTo(EventData.eventDetailsDto)
    }
}