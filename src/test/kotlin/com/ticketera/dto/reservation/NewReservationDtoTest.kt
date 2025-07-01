package com.ticketera.dto.reservation

import com.ticketera.TestData
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import org.assertj.core.api.Assertions
import kotlin.test.Test

class NewReservationDtoTest : TestData() {

    @Test
    fun shouldCreateAReservationFromDto() {
        val dto = NewReservationDto(event.id, ticketType.id, eventSector.id, eventSeat.id)

        val reservation = NewReservationDto.newReservation(dto, purchase, event, ticketType, eventSector, eventSeat)

        Assertions.assertThat(reservation).isInstanceOf(Reservation::class.java)
        Assertions.assertThat(reservation.id).isNotNull()
        Assertions.assertThat(reservation.price).isEqualTo(ticketType.price + (eventSector.priceAddition?: 0.0) + (eventSeat.priceAddition?: 0.0))
        Assertions.assertThat(reservation.status).isEqualTo(ReservationStatus.PENDING)
        Assertions.assertThat(reservation.createdAt).isNotNull()
    }

    @Test
    fun shouldGetDtoFromTheEntity() {
        Assertions.assertThat(NewReservationDto.fromEntity(reservation))
            .isInstanceOf(NewReservationDto::class.java)
    }
}