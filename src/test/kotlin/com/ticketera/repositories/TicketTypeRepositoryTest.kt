package com.ticketera.repositories

import com.ticketera.conf.TestContainerConf
import com.ticketera.data.TicketTypeData
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
        assertThat(ticketTypeRepository.findById(TicketTypeData.ticketType.id).get())
            .isInstanceOfAny(TicketType::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(ticketTypeRepository.findAll().map { it.id })
            .isEqualTo(listOf(TicketTypeData.ticketType.id))
    }

    @Test
    fun shouldFindTicketTypesWithSectorsAndSeatsByEventId() {
        val ticketTypes = ticketTypeRepository.findTicketTypesWithSectorsAndSeatsByEventId(
            TicketTypeData.ticketType.event.id
        )
        assertThat(ticketTypes).isNotEmpty
        assertThat(ticketTypes[0].event).isEqualTo(TicketTypeData.ticketType.event)
        assertThat(ticketTypes[0].event.venue).isEqualTo(TicketTypeData.ticketType.event.venue)
        assertThat(ticketTypes[0].event.banners).isNotEmpty
        assertThat(ticketTypes[0].sectors).isNotEmpty
        assertThat(ticketTypes[0].sectors.first().seats).isNotEmpty
    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}