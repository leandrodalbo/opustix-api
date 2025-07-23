package com.ticketera.dto.ticketTypes

import com.ticketera.data.TicketTypeData
import com.ticketera.data.EventData
import com.ticketera.model.TicketType
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NewTicketTypeDtoTest {

    @Test
    fun shouldCreateATicketTypeFromDto() {
        val ticketType = NewTicketTypeDto.newTicketType(TicketTypeData.newTicketTypeDto, EventData.event)

        assertThat(ticketType).isInstanceOf(TicketType::class.java)
        assertThat(ticketType.id).isNotNull
        assertThat(ticketType.createdAt).isNotNull
    }
}