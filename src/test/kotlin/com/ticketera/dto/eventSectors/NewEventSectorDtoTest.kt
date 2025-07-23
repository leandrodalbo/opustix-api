package com.ticketera.dto.eventSectors

import com.ticketera.TestData
import com.ticketera.model.EventSector
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewEventSectorDtoTest {

    @Test
    fun shouldCreateAnEventSectorFromDto() {
        val sector = NewEventSectorDto.newEventSector(TestData.newEventSectorDto, TestData.ticketType)

        assertThat(sector).isInstanceOf(EventSector::class.java)
        assertThat(sector.id).isNotNull
        assertThat(sector.createdAt).isNotNull
    }

}