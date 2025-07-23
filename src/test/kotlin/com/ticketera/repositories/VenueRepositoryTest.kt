package com.ticketera.repositories

import com.ticketera.conf.TestContainerConf
import com.ticketera.data.VenueData
import com.ticketera.data.EventData
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
        assertThat(venueRepository.findById(VenueData.venue.id).get())
            .isInstanceOfAny(Venue::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(venueRepository.findAll().map { it.id })
            .isEqualTo(listOf(VenueData.venue.id))
    }

    @Test
    fun shouldDeleteById() {
        venueRepository.deleteById(VenueData.venue.id)
        assertThat(venueRepository.findById(VenueData.venue.id))
            .isEmpty
    }

    @Test
    fun shouldFindByEventId() {
        val result = venueRepository.findByEventId(EventData.event.id)
        assertThat(result)
            .isEqualTo(VenueData.venue)
    }

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}