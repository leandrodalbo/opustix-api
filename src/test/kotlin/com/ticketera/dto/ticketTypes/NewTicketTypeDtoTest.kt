package com.ticketera.dto.ticketTypes

import com.ticketera.TestData
import com.ticketera.model.TicketType
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NewTicketTypeDtoTest : TestData() {

    @Test
    fun shouldCreateATicketTypeFromDto() {
        val ticketType = NewTicketTypeDto.newTicketType(newTicketTypeDto, event)

        assertThat(ticketType).isInstanceOf(TicketType::class.java)
        assertThat(ticketType.id).isNotNull
        assertThat(ticketType.createdAt).isNotNull
    }
}