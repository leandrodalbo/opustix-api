package com.ticketera.repositories

import com.ticketera.conf.TestContainerConf
import com.ticketera.data.EventSectorData
import com.ticketera.model.EventSector
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
final class EventSectorRepositoryTest : TestContainerConf() {
    @Autowired
    private lateinit var eventSectorRepository: EventSectorRepository


    @Test
    fun shouldFindById() {
        assertThat(eventSectorRepository.findById(EventSectorData.eventSector.id).get())
            .isInstanceOfAny(EventSector::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(eventSectorRepository.findAll().map { it.id })
            .isEqualTo(listOf(EventSectorData.eventSector.id))
    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}