package com.ticketera.dto.ticketTypes

import com.ticketera.data.TicketTypeData
import com.ticketera.data.EventData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class UpdateTicketTypeDtoTest {

    @Test
    fun shouldGetAnUpdatedTicketTypeFromDto() {
        val updatedTicketType = UpdateTicketTypeDto.updatedTicketType(
            TicketTypeData.updateTicketTypeDto, TicketTypeData.ticketType, EventData.event
        )

        assertThat(updatedTicketType.saleStart).isEqualTo(TicketTypeData.updateTicketTypeDto.startTime)
        assertThat(updatedTicketType.saleEnd).isEqualTo(TicketTypeData.updateTicketTypeDto.endTime)
    }

}