package com.ticketera.model

import com.ticketera.data.EventData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(EventData.event).isNotEqualTo(Event())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(EventData.event.toString()).isEqualTo(
            "Event(id=${EventData.event.id}, title=${EventData.event.title}, description=${EventData.event.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(EventData.event.hashCode()).isEqualTo(EventData.event.id.hashCode())
    }

    @Test
    fun shouldCheckItIsNotFinished() {
        assertThat(EventData.event.hasFinished()).isFalse
    }
}