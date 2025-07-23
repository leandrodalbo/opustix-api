package com.ticketera.repositories

import com.ticketera.conf.TestContainerConf
import com.ticketera.data.PurchaseReservationData
import com.ticketera.data.EventData
import com.ticketera.data.TicketTypeData
import com.ticketera.data.EventSectorData
import com.ticketera.data.EventSeatData
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
        val result = reservationRepository.findById(PurchaseReservationData.reservation.id).get()

        assertThat(result).isInstanceOfAny(Reservation::class.java)
        assertThat(result.purchase).isEqualTo(PurchaseReservationData.purchase)
        assertThat(result.event).isEqualTo(EventData.event)
        assertThat(result.ticketType).isEqualTo(TicketTypeData.ticketType)
        assertThat(result.sector).isEqualTo(EventSectorData.eventSector)
        assertThat(result.seat).isEqualTo(EventSeatData.eventSeat)
    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}