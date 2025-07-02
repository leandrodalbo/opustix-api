package com.ticketera.dto.reservation

import com.ticketera.TestData
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewReservationDtoTest : TestData() {

    @Test
    fun shouldCreateAReservationFromDto() {
        val reservation = NewReservationDto.newReservation(purchase, event, ticketType, eventSector, eventSeat)

        assertThat(reservation).isInstanceOf(Reservation::class.java)
        assertThat(reservation.id).isNotNull()
        assertThat(reservation.price).isEqualTo(142.44)
        assertThat(reservation.status).isEqualTo(ReservationStatus.PENDING)
        assertThat(reservation.createdAt).isNotNull()
    }

}