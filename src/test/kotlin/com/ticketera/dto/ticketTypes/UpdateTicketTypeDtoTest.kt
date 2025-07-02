package com.ticketera.dto.ticketTypes

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UpdateTicketTypeDtoTest {

    @Test
    fun shouldGetAnUpdatedTicketTypeFromDto() {
        val updatedTicketType = UpdateTicketTypeDto.updatedTicketType(
            TestData.updateTicketTypeDto, TestData.ticketType, TestData.event
        )

        assertThat(updatedTicketType.saleStart).isEqualTo(TestData.updateTicketTypeDto.startTime)
        assertThat(updatedTicketType.saleEnd).isEqualTo(TestData.updateTicketTypeDto.endTime)
    }

}