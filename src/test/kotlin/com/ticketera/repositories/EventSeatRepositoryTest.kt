package com.ticketera.repositories

import com.ticketera.TestContainerConf
import com.ticketera.TestData
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
final class EventSeatRepositoryTest : TestContainerConf() {
    @Autowired
    private lateinit var eventSeatRepository: EventSeatRepository


    @Test
    fun shouldFindByEventId() {
        assertThat(eventSeatRepository.findAllByEventId(TestData.event.id))
            .isEqualTo(listOf(TestData.eventSeat))
    }

    @Test
    fun shouldFindByEventIdAndSectorId() {
        assertThat(eventSeatRepository.findAllByEventIdAndSectorId(TestData.event.id, TestData.eventSector.id))
            .isEqualTo(listOf(TestData.eventSeat))
    }

    @Test
    fun shouldDeleteByEventId() {
        eventSeatRepository.deleteByEventId(TestData.event.id)
        assertThat(eventSeatRepository.findById(TestData.eventSeat.id))
            .isEmpty
    }

    @Test
    fun shouldDeleteByEventIdAndSectorId() {
        eventSeatRepository.deleteByEventIdAndSectorId(TestData.event.id, TestData.eventSector.id)
        assertThat(eventSeatRepository.findById(TestData.eventSeat.id))
            .isEmpty
    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}