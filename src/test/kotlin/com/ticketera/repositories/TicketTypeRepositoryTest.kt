package com.ticketera.repositories

import com.ticketera.TestData
import com.ticketera.model.TicketType
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
final class TicketTypeRepositoryTest : TestData() {
    @Autowired
    private lateinit var ticketTypeRepository: TicketTypeRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager


    @BeforeEach
    fun setup() {
        val savedVenue = entityManager.persistAndFlush(venue)
        entityManager.persistAndFlush(event.copy(venue = savedVenue))
        entityManager.persistAndFlush(ticketType.copy(event = event))
    }


    @Test
    fun shouldFindById() {
        assertThat(ticketTypeRepository.findById(ticketTypeId).get())
            .isInstanceOfAny(TicketType::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(ticketTypeRepository.findAll().map { it.id })
            .isEqualTo(listOf(ticketTypeId))
    }

    @Test
    fun shouldDeleteByEventId() {
        ticketTypeRepository.deleteByEventId(eventId)
        assertThat(ticketTypeRepository.findById(ticketTypeId))
            .isEmpty
    }

    @Test
    fun shouldFindByEventId() {
        assertThat(ticketTypeRepository.findByEventId(eventId))
            .isInstanceOfAny(TicketType::class.java)
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