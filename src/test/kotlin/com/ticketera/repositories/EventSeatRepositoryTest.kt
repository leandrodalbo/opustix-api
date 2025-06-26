package com.ticketera.repositories

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final class EventSeatRepositoryTest : TestData() {
    @Autowired
    private lateinit var eventSeatRepository: EventSeatRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager


    @BeforeEach
    fun setup() {
        entityManager.persistAndFlush(venue)
        entityManager.persistAndFlush(event)
        entityManager.persistAndFlush(eventSector)
        entityManager.persistAndFlush(eventSeat)
    }


    @Test
    fun shouldFindByEventId() {
        assertThat(eventSeatRepository.findAllByEventId(eventId))
            .isEqualTo(listOf(eventSeat))
    }

    @Test
    fun shouldFindByEventIdAndSectorId() {
        assertThat(eventSeatRepository.findAllByEventIdAndSectorId(eventId, eventSectorId))
            .isEqualTo(listOf(eventSeat))
    }

    @Test
    fun shouldDeleteByEventId() {
        eventSeatRepository.deleteByEventId(eventId)
        assertThat(eventSeatRepository.findById(eventSeatId))
            .isEmpty
    }

    @Test
    fun shouldDeleteByEventIdAndSectorId() {
        eventSeatRepository.deleteByEventIdAndSectorId(eventId, eventSectorId)
        assertThat(eventSeatRepository.findById(eventSeatId))
            .isEmpty
    }


    companion object {
        @Container
        val postgres = TestContainerConf.postgres

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) =
            TestContainerConf.registerProperties(registry)
    }
}