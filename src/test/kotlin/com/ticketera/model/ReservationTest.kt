package com.ticketera.model

import com.ticketera.data.PurchaseReservationData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(PurchaseReservationData.reservation).isNotEqualTo(Reservation())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(PurchaseReservationData.reservation.toString()).isEqualTo(
            "Reservation(id=${PurchaseReservationData.reservation.id}, status=${PurchaseReservationData.reservation.status}, createdAt=${PurchaseReservationData.reservation.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(PurchaseReservationData.reservation.hashCode()).isEqualTo(PurchaseReservationData.reservation.id.hashCode())
    }
}