package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSeatTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.eventSeat).isNotEqualTo(EventSeat())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.eventSeat.toString()).isEqualTo(
            "EventSeat(id=${TestData.eventSeat.id}, label=${TestData.eventSeat.label})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.eventSeat.hashCode()).isEqualTo(TestData.eventSeat.id.hashCode())
    }
}