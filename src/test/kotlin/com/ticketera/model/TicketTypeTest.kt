package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTypeTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.ticketType).isNotEqualTo(TicketType())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.ticketType.toString()).isEqualTo(
            "TicketType(id=${TestData.ticketType.id}, name=${TestData.ticketType.name}, description=${TestData.ticketType.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.ticketType.hashCode()).isEqualTo(TestData.ticketType.id.hashCode())
    }
}