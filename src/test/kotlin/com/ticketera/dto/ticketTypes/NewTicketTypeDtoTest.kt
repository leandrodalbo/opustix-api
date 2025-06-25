package com.ticketera.dto.ticketTypes

import com.ticketera.TestData
import com.ticketera.model.TicketType
import org.assertj.core.api.Assertions
import kotlin.test.Test

class NewTicketTypeDtoTest : TestData() {

    @Test
    fun shouldCreateATicketTypeFromDto() {
        val ticketType = NewTicketTypeDto.newTicketType(newTicketTypeDto, event)

        Assertions.assertThat(ticketType).isInstanceOf(TicketType::class.java)
        Assertions.assertThat(ticketType.id).isNotNull
        Assertions.assertThat(ticketType.createdAt).isNotNull
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        Assertions.assertThat(NewTicketTypeDto.fromEntity(ticketType))
            .isInstanceOf(NewTicketTypeDto::class.java)
    }
}