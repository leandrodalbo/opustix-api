package com.ticketera.dto.reservation

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class ReservationDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = ReservationDto.fromEntity(TestData.reservation)

        assertThat(dto).isNotNull
        assertThat(dto).isEqualTo(TestData.reservationDto)
    }
}