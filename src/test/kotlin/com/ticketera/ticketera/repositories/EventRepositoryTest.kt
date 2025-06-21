package com.ticketera.ticketera.repositories

import com.ticketera.ticketera.model.Event
import com.ticketera.ticketera.model.Venue
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
import java.time.Instant
import java.util.UUID

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final class EventRepositoryTest {
    @Autowired
    private lateinit var eventRepository: EventRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager

    private val venueId = UUID.randomUUID()
    private val eventId = UUID.randomUUID()

    @BeforeEach
    fun setup() {
        val venue = entityManager.persistAndFlush(
            Venue(
                venueId,
                "venue-0",
                address = "Road x at 1324",
                Instant.now().toEpochMilli()
            )
        )

        entityManager.persistAndFlush(
            Event(
                eventId,
                "event-x",
                "event-x",
                Instant.now().toEpochMilli(),
                Instant.now().toEpochMilli(),
                1000,
                venue,
                Instant.now().toEpochMilli()
            )
        )

    }


    @Test
    fun shouldFindById() {
        assertThat(eventRepository.findById(eventId).get())
            .isInstanceOfAny(Event::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(eventRepository.findAll().map { it.id })
            .isEqualTo(listOf(eventId))
    }

    @Test
    fun shouldDeleteById() {
        eventRepository.deleteById(eventId)
        assertThat(eventRepository.findById(venueId))
            .isEmpty
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