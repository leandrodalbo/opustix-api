package com.ticketera.dto.eventSectors

import com.ticketera.TestData
import com.ticketera.model.EventSector
import org.assertj.core.api.Assertions
import kotlin.test.Test

class NewEventSectorDtoTest : TestData() {

    @Test
    fun shouldCreateAnEventSectorFromDto() {
        val ticketType = NewEventSectorDto.newEventSector(newEventSectorDto, event)

        Assertions.assertThat(ticketType).isInstanceOf(EventSector::class.java)
        Assertions.assertThat(ticketType.id).isNotNull
        Assertions.assertThat(ticketType.createdAt).isNotNull
    }

}