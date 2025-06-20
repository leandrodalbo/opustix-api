package com.ticketera.ticketera.repositories

import com.ticketera.ticketera.ContainerTests
import com.ticketera.ticketera.model.Event
import com.ticketera.ticketera.model.Venue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.testcontainers.junit.jupiter.Testcontainers
import java.time.Instant
import java.util.UUID

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
class EventRepositoryTest : ContainerTests() {

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

}