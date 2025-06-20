package com.ticketera.ticketera.repositories

import com.ticketera.ticketera.ContainerTests
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
class VenueRepositoryTest : ContainerTests() {

    @Autowired
    private lateinit var venueRepository: VenueRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager
    private val venueId = UUID.randomUUID()

    @BeforeEach
    fun setup() {
        entityManager.persistAndFlush(
            Venue(
                venueId,
                "venue-0",
                address = "Road x at 1324",
                Instant.now().toEpochMilli()
            )
        )

    }

    @Test
    fun shouldFindById() {
        assertThat(venueRepository.findById(venueId).get())
            .isInstanceOfAny(Venue::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(venueRepository.findAll().map { it.id })
            .isEqualTo(listOf(venueId))
    }

    @Test
    fun shouldDeleteById() {
        venueRepository.deleteById(venueId)
        assertThat(venueRepository.findById(venueId))
            .isEmpty
    }
}