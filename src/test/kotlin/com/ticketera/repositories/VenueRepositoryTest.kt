package com.ticketera.repositories

import com.ticketera.model.Venue
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
final class VenueRepositoryTest {
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