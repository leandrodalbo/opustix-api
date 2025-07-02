package com.ticketera.repositories

import com.ticketera.TestContainerConf
import com.ticketera.TestData
import com.ticketera.model.Reservation
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
final class ReservationRepositoryTest : TestContainerConf() {

    @Autowired
    private lateinit var reservationRepository: ReservationRepository


    @Test
    fun shouldFindByIdWithAllTheData() {
        val result = reservationRepository.findById(TestData.reservation.id).get()

        assertThat(result).isInstanceOfAny(Reservation::class.java)
        assertThat(result.purchase).isEqualTo(TestData.purchase)
        assertThat(result.event).isEqualTo(TestData.event)
        assertThat(result.ticketType).isEqualTo(TestData.ticketType)
        assertThat(result.sector).isEqualTo(TestData.eventSector)
        assertThat(result.seat).isEqualTo(TestData.eventSeat)
    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}