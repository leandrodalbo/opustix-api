package com.ticketera.dto.reservation

import com.ticketera.data.PurchaseReservationData
import com.ticketera.data.EventData
import com.ticketera.data.TicketTypeData
import com.ticketera.data.EventSectorData
import com.ticketera.data.EventSeatData
import com.ticketera.aux.PendingReservation
import com.ticketera.model.Reservation
import com.ticketera.model.ReservationStatus
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class NewReservationDtoTest {

    @Test
    fun shouldCreateAReservationFromDto() {
        val reservation = NewReservationDto.newReservation(
            PurchaseReservationData.purchase,
            PendingReservation(
                EventData.event,
                TicketTypeData.ticketType,
                EventSectorData.eventSector,
                EventSeatData.eventSeat,
                142.44
            )
        )

        assertThat(reservation).isInstanceOf(Reservation::class.java)
        assertThat(reservation.id).isNotNull()
        assertThat(reservation.price).isEqualTo(142.44)
        assertThat(reservation.status).isEqualTo(ReservationStatus.PENDING)
        assertThat(reservation.createdAt).isNotNull()
    }

}