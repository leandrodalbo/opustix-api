package com.ticketera.model

import com.ticketera.data.TicketTypeData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketTypeTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TicketTypeData.ticketType).isNotEqualTo(TicketType())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TicketTypeData.ticketType.toString()).isEqualTo(
            "TicketType(id=${TicketTypeData.ticketType.id}, name=${TicketTypeData.ticketType.name}, description=${TicketTypeData.ticketType.description})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TicketTypeData.ticketType.hashCode()).isEqualTo(TicketTypeData.ticketType.id.hashCode())
    }
}