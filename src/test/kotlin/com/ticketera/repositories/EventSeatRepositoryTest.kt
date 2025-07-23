package com.ticketera.repositories

import com.ticketera.conf.TestContainerConf
import com.ticketera.data.EventSeatData
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
final class EventSeatRepositoryTest : TestContainerConf() {
    @Autowired
    private lateinit var eventSeatRepository: EventSeatRepository

    @Test
    fun shouldFindByAll() {
        assertThat(eventSeatRepository.findAll().map { it.id })
            .isEqualTo(listOf(EventSeatData.eventSeat.id))
    }

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}