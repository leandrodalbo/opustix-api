package com.ticketera.dto.eventSeats

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewEventSeatsDtoTest {

    @Test
    fun shouldCreateAListOfEventSeats() {
        val seats = NewEventSeatsDto.newEventSeats(TestData.newEventSeatsDto, TestData.event, TestData.eventSector)

        assertThat(seats).isInstanceOf(List::class.java)
        assertThat(seats[0].seatNumber).isEqualTo("1")
        assertThat(seats[4].seatNumber).isEqualTo("5")
    }
}