package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.event).isNotEqualTo(Event())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.event.toString()).isEqualTo(
            "Event(id=${TestData.event.id}, title=${TestData.event.title}, description=${TestData.event.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.event.hashCode()).isEqualTo(TestData.event.id.hashCode())
    }

    @Test
    fun shouldCheckItIsNotFinished() {
        assertThat(TestData.event.hasFinished()).isFalse
    }
}