package com.ticketera.dto.ticketTypes

import com.ticketera.data.TicketTypeData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class TicketTypeDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = TicketTypeDto.fromEntity(
            TicketTypeData.ticketType
        )
        assertThat(dto).isEqualTo(TicketTypeData.ticketTypeDto)
    }
}