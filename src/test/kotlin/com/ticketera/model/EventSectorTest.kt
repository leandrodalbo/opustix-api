package com.ticketera.model

import com.ticketera.data.EventSectorData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventSectorTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(EventSectorData.eventSector).isNotEqualTo(EventSector())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(EventSectorData.eventSector.toString()).isEqualTo(
            "EventSector(id=${EventSectorData.eventSector.id}, name=${EventSectorData.eventSector.name}, description=${EventSectorData.eventSector.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(EventSectorData.eventSector.hashCode()).isEqualTo(EventSectorData.eventSector.id.hashCode())
    }
}