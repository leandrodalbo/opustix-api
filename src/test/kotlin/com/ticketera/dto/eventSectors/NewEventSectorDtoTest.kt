package com.ticketera.dto.eventSectors

import com.ticketera.TestData
import com.ticketera.model.EventSector
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewEventSectorDtoTest : TestData() {

    @Test
    fun shouldCreateAnEventSectorFromDto() {
        val ticketType = NewEventSectorDto.newEventSector(newEventSectorDto, event)

        assertThat(ticketType).isInstanceOf(EventSector::class.java)
        assertThat(ticketType.id).isNotNull
        assertThat(ticketType.createdAt).isNotNull
    }

}