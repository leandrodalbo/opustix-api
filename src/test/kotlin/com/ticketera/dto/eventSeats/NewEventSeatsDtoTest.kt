package com.ticketera.dto.eventSeats

import com.ticketera.TestData
import org.assertj.core.api.Assertions
import kotlin.test.Test

class NewEventSeatsDtoTest : TestData() {

    @Test
    fun shouldCreateAListOfEventSeats() {
        val seats = NewEventSeatsDto.newEventSeats(newEventSeatsDto, event, eventSector)

        Assertions.assertThat(seats).isInstanceOf(List::class.java)
        Assertions.assertThat(seats[0].seatNumber).isEqualTo("1")
        Assertions.assertThat(seats[4].seatNumber).isEqualTo("5")
    }
}