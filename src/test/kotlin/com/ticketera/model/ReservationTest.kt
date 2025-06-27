package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(reservation).isEqualTo(reservation.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(reservation.toString()).isEqualTo(
        "Reservation(id=$reservationId, status=${reservation.status}, createdAt=${reservation.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(reservation.hashCode()).isEqualTo(reservation.copy().hashCode())
    }
}