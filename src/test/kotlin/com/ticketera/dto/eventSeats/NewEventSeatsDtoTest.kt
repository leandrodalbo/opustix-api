package com.ticketera.dto.eventSeats

import com.ticketera.data.EventSeatData
import com.ticketera.data.EventSectorData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewEventSeatsDtoTest {

    @Test
    fun shouldCreateAListOfEventSeats() {
        val seats = NewEventSeatsDto.newEventSeats(EventSeatData.newEventSeatsDto, EventSectorData.eventSector)

        assertThat(seats).isInstanceOf(List::class.java)
        assertThat(seats[0].seatNumber).isEqualTo("1")
        assertThat(seats[4].seatNumber).isEqualTo("5")
    }
}