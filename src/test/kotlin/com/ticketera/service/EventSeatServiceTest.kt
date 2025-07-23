package com.ticketera.service

import com.ticketera.TestData
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
        every { eventSeatRepository.saveAll(any<List<EventSeat>>()) } returns listOf(TestData.eventSeat)
        every { eventSectorRepository.findById(any()) } returns Optional.of(TestData.eventSector)

        val saved = eventSeatService.generateEventSeats(TestData.newEventSeatsDto)

        assertThat(saved).isEqualTo(listOf(TestData.eventSeat))

        verify { eventSectorRepository.findById(any()) }
        verify { eventSeatRepository.saveAll(any<List<EventSeat>>()) }
    }


    @Test
    fun shouldNotSaveSeatsWithoutASector() {
        every { eventSectorRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                eventSeatService.generateEventSeats(TestData.newEventSeatsDto)
            }
        verify {
            eventSectorRepository.findById(any())
        }

    }
}