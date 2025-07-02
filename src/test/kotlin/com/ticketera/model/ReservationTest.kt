package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.reservation).isNotEqualTo(Reservation())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.reservation.toString()).isEqualTo(
            "Reservation(id=${TestData.reservation.id}, status=${TestData.reservation.status}, createdAt=${TestData.reservation.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.reservation.hashCode()).isEqualTo(TestData.reservation.id.hashCode())
    }
}