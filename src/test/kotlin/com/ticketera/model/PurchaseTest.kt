package com.ticketera.model

import com.ticketera.data.PurchaseReservationData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchaseTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(PurchaseReservationData.purchase).isNotEqualTo(Purchase())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(PurchaseReservationData.purchase.toString()).isEqualTo(
            "Purchase(id=${PurchaseReservationData.purchase.id}, userInfo=${PurchaseReservationData.purchase.userInfo}, totalPrice=${PurchaseReservationData.purchase.totalPrice}, paymentStatus=${PurchaseReservationData.purchase.paymentStatus}, expiresAt=${PurchaseReservationData.purchase.expiresAt}, createdAt=${PurchaseReservationData.purchase.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(PurchaseReservationData.purchase.hashCode()).isEqualTo(PurchaseReservationData.purchase.id.hashCode())
    }

    @Test
    fun shouldCheckItIsNotExpired() {
        assertThat(PurchaseReservationData.purchase.isExpired()).isFalse
    }
}