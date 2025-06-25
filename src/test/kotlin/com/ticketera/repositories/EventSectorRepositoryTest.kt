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
import org.testcontainers.containers.PostgreSQLContainer
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
        val postgres = PostgreSQLContainer<Nothing>("postgres:16.4")
            .apply {
                withDatabaseName("ticketera")
                withUsername("ticketera")
                withPassword("dGVzdGRi")
                withReuse(true)
            }

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.flyway.url", postgres::getJdbcUrl)
            registry.add("spring.flyway.user", postgres::getUsername)
            registry.add("spring.flyway.password", postgres::getPassword)
        }
    }
}