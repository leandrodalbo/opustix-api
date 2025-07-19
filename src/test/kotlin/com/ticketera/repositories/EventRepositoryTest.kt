package com.ticketera.repositories

import com.ticketera.TestContainerConf
import com.ticketera.TestData
import com.ticketera.model.Event
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final class EventRepositoryTest : TestContainerConf() {
    @Autowired
    private lateinit var eventRepository: EventRepository


    @Test
    fun shouldFindById() {
        assertThat(eventRepository.findById(TestData.event.id).get())
            .isInstanceOfAny(Event::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(eventRepository.findAll().map { it.id })
            .isEqualTo(listOf(TestData.event.id))
    }

    @Test
    fun shouldLoadBanners() {
        assertThat(eventRepository.findById(TestData.event.id).get().banners.toList())
            .isEqualTo(listOf(TestData.banner))
    }

    @Test
    fun shouldDeleteById() {
        eventRepository.deleteById(TestData.event.id)
        assertThat(eventRepository.findById(TestData.venue.id))
            .isEmpty
    }


    @Test
    fun shouldFetchEventDetails() {
        val result = eventRepository.fetchEventDetailsRaw(TestData.event.id)[0]

        assertThat(result["ticket_id"]).isEqualTo(TestData.ticketType.id)
        assertThat(result["sector_id"]).isEqualTo(TestData.eventSector.id)
        assertThat(result["seat_id"]).isEqualTo(TestData.eventSeat.id)
        assertThat(result["event_id"]).isEqualTo(TestData.event.id)

    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}