package com.ticketera.service

import com.ticketera.data.TicketTypeData
import com.ticketera.data.EventData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.EventRepository
import com.ticketera.repositories.TicketTypeRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

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
        every { ticketTypeRepository.save(any()) } returns TicketTypeData.ticketType
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)

        val saved = ticketTypeService.addTicketType(TicketTypeData.newTicketTypeDto)

        assertThat(saved).isEqualTo(TicketTypeData.ticketType)

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.save(any()) }
    }

    @Test
    fun shouldNotSaveATicketTypeWithoutAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.addTicketType(TicketTypeData.newTicketTypeDto)
            }

        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateATicketType() {
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TicketTypeData.ticketType)
        every { ticketTypeRepository.save(any()) } returns TicketTypeData.ticketType

        val saved = ticketTypeService.updateTicketType(TicketTypeData.updateTicketTypeDto)

        assertThat(saved).isEqualTo(TicketTypeData.ticketType)

        verify { ticketTypeRepository.save(any()) }
        verify { ticketTypeRepository.findById(any()) }
        verify { eventRepository.findById(any()) }
    }

    @Test
    fun shouldUpdateItWithoutEvents() {
        every { ticketTypeRepository.findById(any()) } returns Optional.of(TicketTypeData.ticketType)
        every { ticketTypeRepository.save(any()) } returns TicketTypeData.ticketType

        val saved = ticketTypeService.updateTicketType(TicketTypeData.updateTicketTypeDto.copy(eventId = null))

        assertThat(saved).isEqualTo(TicketTypeData.ticketType)

        verify { ticketTypeRepository.save(any()) }
        verify { ticketTypeRepository.findById(any()) }

    }

    @Test
    fun shouldNotUpdateItIfNotFound() {
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)
        every { ticketTypeRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                ticketTypeService.updateTicketType(TicketTypeData.updateTicketTypeDto)
            }

        verify { eventRepository.findById(any()) }
        verify { ticketTypeRepository.findById(any()) }
    }

}