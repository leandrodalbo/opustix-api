package com.ticketera.dto.ticketTypes

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class TicketTypeDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = TicketTypeDto.fromEntity(
            TestData.ticketType
        )
        assertThat(dto).isEqualTo(TestData.ticketTypeDto)
    }
}