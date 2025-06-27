package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSectorTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(eventSector).isEqualTo(eventSector.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(eventSector.toString()).isEqualTo(
            "EventSector(id=${eventSector.id}, name=${eventSector.name}, description=${eventSector.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(eventSector.hashCode()).isEqualTo(eventSector.copy().hashCode())
    }
}