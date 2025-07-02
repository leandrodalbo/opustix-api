package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSectorTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.eventSector).isNotEqualTo(EventSector())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.eventSector.toString()).isEqualTo(
            "EventSector(id=${TestData.eventSector.id}, name=${TestData.eventSector.name}, description=${TestData.eventSector.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.eventSector.hashCode()).isEqualTo(TestData.eventSector.id.hashCode())
    }
}