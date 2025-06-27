package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSeatTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(eventSeat).isEqualTo(eventSeat.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(eventSeat.toString()).isEqualTo(
            "EventSeat(id=${eventSeat.id}, label=${eventSeat.label})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(eventSeat.hashCode()).isEqualTo(eventSeat.copy().hashCode())
    }
}