package com.ticketera.repositories

import com.ticketera.TestContainerConf
import com.ticketera.TestData
import com.ticketera.model.Venue
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
final class VenueRepositoryTest : TestContainerConf() {

    @Autowired
    private lateinit var venueRepository: VenueRepository

    @Test
    fun shouldFindById() {
        assertThat(venueRepository.findById(TestData.venue.id).get())
            .isInstanceOfAny(Venue::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(venueRepository.findAll().map { it.id })
            .isEqualTo(listOf(TestData.venue.id))
    }

    @Test
    fun shouldDeleteById() {
        venueRepository.deleteById(TestData.venue.id)
        assertThat(venueRepository.findById(TestData.venue.id))
            .isEmpty
    }

    @Test
    fun shouldFindByEventId() {
        val result = venueRepository.findByEventId(TestData.event.id)
        assertThat(result)
            .isEqualTo(TestData.venue)
    }

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}