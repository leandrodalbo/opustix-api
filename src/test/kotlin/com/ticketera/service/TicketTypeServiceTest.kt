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

class TicketTypeServiceTest : TestData() {

    private val eventRepository: EventRepository = mockk()
    private val ticketTypeRepository: TicketTypeRepository = mockk()

    private val ticketTypeService = TicketTypeService(
        ticketTypeRepository,
        eventRepository
    )

    @Test
    fun shouldSaveAnewTicketType() {
        every { ticketTypeRepository.save(any()) } returns ticketType
        every { eventRepository.findById(any()) } returns Optional.of(event)

        val saved = ticketTypeService.addTicketType(newTicketTypeDto)

        assertThat(saved).isEqualTo(ticketType)

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.save(any()) }
    }

    @Test
    fun shouldNotSaveATicketTypeWithoutAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.addTicketType(newTicketTypeDto)
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateATicketType() {
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { ticketTypeRepository.findById(any()) } returns Optional.of(ticketType)
        every { ticketTypeRepository.save(any()) } returns ticketType

        val saved = ticketTypeService.updateTicketType(updateTicketTypeDto)

        assertThat(saved).isEqualTo(ticketType)

        verify { ticketTypeRepository.save(any()) }
        verify { ticketTypeRepository.findById(any()) }
        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateItWithoutEvents() {
        every { ticketTypeRepository.findById(any()) } returns Optional.of(ticketType)
        every { ticketTypeRepository.save(any()) } returns ticketType

        val saved = ticketTypeService.updateTicketType(updateTicketTypeDto.copy(eventId = null))

        assertThat(saved).isEqualTo(ticketType)

        verify { ticketTypeRepository.save(any()) }
        verify { ticketTypeRepository.findById(any()) }

    }

    @Test
    fun shouldNotUpdateItIfNotFound() {
        every { eventRepository.findById(any()) } returns Optional.of(event)
        every { ticketTypeRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.updateTicketType(updateTicketTypeDto)
            }

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }
    }

    @Test
    fun shouldDeleteByEventId() {
        every { ticketTypeRepository.deleteByEventId(any()) } just runs

        ticketTypeService.deleteByEventId(eventId)

        verify { ticketTypeRepository.deleteByEventId(any()) }
    }

    @Test
    fun shouldFetchAllByEventId() {
        every { ticketTypeRepository.findAllByEventId(any()) } returns listOf(ticketType)

        assertThat(ticketTypeService.findByEventId(eventId))
            .isEqualTo(listOf(ticketType))


        verify { ticketTypeRepository.findAllByEventId(any()) }
    }
}