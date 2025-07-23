package com.ticketera.service

import com.ticketera.data.EventSectorData
import com.ticketera.data.EventSeatData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.EventSeat
import com.ticketera.repositories.EventSeatRepository
import com.ticketera.repositories.EventSectorRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class EventSeatServiceTest {

    private val eventSectorRepository: EventSectorRepository = mockk()
    private val eventSeatRepository: EventSeatRepository = mockk()

    private val eventSeatService = EventSeatService(
        eventSeatRepository,
        eventSectorRepository
    )

    @Test
    fun shouldGenerateTheSeatsAnSaveThem() {
        every { eventSeatRepository.saveAll(any<List<EventSeat>>()) } returns listOf(EventSeatData.eventSeat)
        every { eventSectorRepository.findById(any()) } returns Optional.of(EventSectorData.eventSector)

        val saved = eventSeatService.generateEventSeats(EventSeatData.newEventSeatsDto)

        assertThat(saved).isEqualTo(listOf(EventSeatData.eventSeat))

        verify { eventSectorRepository.findById(any()) }
        verify { eventSeatRepository.saveAll(any<List<EventSeat>>()) }
    }


    @Test
    fun shouldNotSaveSeatsWithoutASector() {
        every { eventSectorRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventSeatService.generateEventSeats(EventSeatData.newEventSeatsDto)
            }
        verify {
            eventSectorRepository.findById(any())
        }

    }
}