package com.ticketera.service

import com.ticketera.TestData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.TicketTypeRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.just
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Optional

class TicketTypeServiceTest {

    private val eventRepository: EventRepository = mockk()
    private val ticketTypeRepository: TicketTypeRepository = mockk()

    private val ticketTypeService = TicketTypeService(
        ticketTypeRepository,
        eventRepository
    )

    @Test
    fun shouldSaveAnewTicketType() {
        every { ticketTypeRepository.save(any()) } returns TestData.ticketType
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)

        val saved = ticketTypeService.addTicketType(TestData.newTicketTypeDto)

        assertThat(saved).isEqualTo(TestData.ticketType)

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.save(any()) }
    }

    @Test
    fun shouldNotSaveATicketTypeWithoutAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.addTicketType(TestData.newTicketTypeDto)
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateATicketType() {
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TestData.ticketType)
        every { ticketTypeRepository.save(any()) } returns TestData.ticketType

        val saved = ticketTypeService.updateTicketType(TestData.updateTicketTypeDto)

        assertThat(saved).isEqualTo(TestData.ticketType)

        verify { ticketTypeRepository.save(any()) }
        verify { ticketTypeRepository.findById(any()) }
        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateItWithoutEvents() {
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TestData.ticketType)
        every { ticketTypeRepository.save(any()) } returns TestData.ticketType

        val saved = ticketTypeService.updateTicketType(TestData.updateTicketTypeDto.copy(eventId = null))

        assertThat(saved).isEqualTo(TestData.ticketType)

        verify { ticketTypeRepository.save(any()) }
        verify { ticketTypeRepository.findById(any()) }

    }

    @Test
    fun shouldNotUpdateItIfNotFound() {
        every { eventRepository.findById(any()) } returns Optional.of(TestData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.updateTicketType(TestData.updateTicketTypeDto)
            }

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }
    }

    @Test
    fun shouldDeleteByEventId() {
        every { ticketTypeRepository.deleteByEventId(any()) } just runs

        ticketTypeService.deleteByEventId(TestData.event.id)

        verify { ticketTypeRepository.deleteByEventId(any()) }
    }

    @Test
    fun shouldFetchAllByEventId() {
        every { ticketTypeRepository.findAllByEventId(any()) } returns listOf(TestData.ticketType)

        assertThat(ticketTypeService.findByEventId(TestData.event.id))
            .isEqualTo(listOf(TestData.ticketType))


        verify { ticketTypeRepository.findAllByEventId(any()) }
    }
}