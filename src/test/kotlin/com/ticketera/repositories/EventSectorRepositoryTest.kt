package com.ticketera.repositories

import com.ticketera.TestData
import com.ticketera.model.EventSector
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
final class EventSectorRepositoryTest : TestData() {
    @Autowired
    private lateinit var eventSectorRepository: EventSectorRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager


    @BeforeEach
    fun setup() {
        entityManager.persistAndFlush(venue)
        entityManager.persistAndFlush(event.copy(venue = venue))
        entityManager.persistAndFlush(eventSector.copy(event = event))
    }


    @Test
    fun shouldFindById() {
        assertThat(eventSectorRepository.findById(eventSectorId).get())
            .isInstanceOfAny(EventSector::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(eventSectorRepository.findAll().map { it.id })
            .isEqualTo(listOf(eventSectorId))
    }

    @Test
    fun shouldDeleteByEventId() {
        eventSectorRepository.deleteByEventId(eventId)
        assertThat(eventSectorRepository.findById(eventSectorId))
            .isEmpty
    }

    @Test
    fun shouldFindByEventId() {
        assertThat(eventSectorRepository.findAllByEventId(eventId))
            .isEqualTo(listOf(eventSector))
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