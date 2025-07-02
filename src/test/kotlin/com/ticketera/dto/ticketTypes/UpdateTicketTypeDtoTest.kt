package com.ticketera.dto.ticketTypes

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UpdateTicketTypeDtoTest : TestData() {

    @Test
    fun shouldGetAnUpdatedTicketTypeFromDto() {
        val updatedTicketType = UpdateTicketTypeDto.updatedTicketType(
            updateTicketTypeDto, ticketType, event
        )

        assertThat(updatedTicketType.saleStart).isEqualTo(updateTicketTypeDto.startTime)
        assertThat(updatedTicketType.saleEnd).isEqualTo(updateTicketTypeDto.endTime)
    }

}