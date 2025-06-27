package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EventTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(event).isEqualTo(event.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(event.toString()).isEqualTo(
            "Event(id=${event.id}, title=${event.title}, description=${event.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(event.hashCode()).isEqualTo(event.copy().hashCode())
    }
}