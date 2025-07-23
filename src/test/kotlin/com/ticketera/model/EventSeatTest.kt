package com.ticketera.model

import com.ticketera.data.EventSeatData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSeatTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(EventSeatData.eventSeat).isNotEqualTo(EventSeat())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(EventSeatData.eventSeat.toString()).isEqualTo(
            "EventSeat(id=${EventSeatData.eventSeat.id}, label=${EventSeatData.eventSeat.label})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(EventSeatData.eventSeat.hashCode()).isEqualTo(EventSeatData.eventSeat.id.hashCode())
    }
}