package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTypeTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(ticketType).isEqualTo(ticketType.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(ticketType.toString()).isEqualTo(
            "TicketType(id=${ticketType.id}, name=${ticketType.name}, description=${ticketType.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(ticketType.hashCode()).isEqualTo(ticketType.copy().hashCode())
    }
}