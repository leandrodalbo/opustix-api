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
        entityManager.persistAndFlush(venue)
        entityManager.persistAndFlush(event)
        entityManager.persistAndFlush(ticketType)
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
        assertThat(ticketTypeRepository.findAllByEventId(eventId))
            .isEqualTo(listOf(ticketType))
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