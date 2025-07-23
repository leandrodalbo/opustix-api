package com.ticketera.repositories

import com.ticketera.TestContainerConf
import com.ticketera.TestData
import com.ticketera.model.TicketType
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
final class TicketTypeRepositoryTest : TestContainerConf() {

    @Autowired
    private lateinit var ticketTypeRepository: TicketTypeRepository


    @Test
    fun shouldFindById() {
        assertThat(ticketTypeRepository.findById(TestData.ticketType.id).get())
            .isInstanceOfAny(TicketType::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(ticketTypeRepository.findAll().map { it.id })
            .isEqualTo(listOf(TestData.ticketType.id))
    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}