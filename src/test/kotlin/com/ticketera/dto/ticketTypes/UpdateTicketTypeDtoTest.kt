package com.ticketera.dto.ticketTypes

import com.ticketera.TestData
import org.assertj.core.api.Assertions
import kotlin.test.Test

class UpdateTicketTypeDtoTest : TestData() {

    @Test
    fun shouldGetAnUpdatedVenueFromDto() {
        val updatedTicketType = UpdateTicketTypeDto.updatedTicketType(
            updateTicketTypeDto, ticketType, event
        )

        Assertions.assertThat(updatedTicketType.startTime).isEqualTo(updateTicketTypeDto.startTime)
        Assertions.assertThat(updatedTicketType.endTime).isEqualTo(updateTicketTypeDto.endTime)
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        Assertions.assertThat(
            UpdateTicketTypeDto.fromEntity(
                ticketType
            )
        ).isInstanceOf(UpdateTicketTypeDto::class.java)
    }
}