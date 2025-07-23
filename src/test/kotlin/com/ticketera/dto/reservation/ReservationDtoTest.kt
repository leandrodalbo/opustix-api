package com.ticketera.dto.reservation

import com.ticketera.data.PurchaseReservationData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ReservationDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = ReservationDto.fromEntity(PurchaseReservationData.reservation)

        assertThat(dto).isNotNull
        assertThat(dto).isEqualTo(PurchaseReservationData.reservationDto)
    }
}