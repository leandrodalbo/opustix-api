package com.ticketera.dto.reservation

import com.ticketera.TestData
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewReservationDtoTest {

    @Test
    fun shouldCreateAReservationFromDto() {
        val reservation = NewReservationDto.newReservation(
            TestData.purchase,
            TestData.event,
            TestData.ticketType,
            TestData.eventSector,
            TestData.eventSeat
        )

        assertThat(reservation).isInstanceOf(Reservation::class.java)
        assertThat(reservation.id).isNotNull()
        assertThat(reservation.price).isEqualTo(142.44)
        assertThat(reservation.status).isEqualTo(ReservationStatus.PENDING)
        assertThat(reservation.createdAt).isNotNull()
    }

}