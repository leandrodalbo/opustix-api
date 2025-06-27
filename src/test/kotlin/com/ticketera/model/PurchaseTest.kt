package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchaseTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(purchase).isEqualTo(purchase.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(purchase.toString()).isEqualTo(
            "Purchase(id=${purchase.id}, userInfo=${purchase.userInfo}, totalPrice=${purchase.totalPrice}, paymentStatus=${purchase.paymentStatus}, expiresAt=${purchase.expiresAt}, createdAt=${purchase.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(purchase.hashCode()).isEqualTo(purchase.copy().hashCode())
    }

    @Test
    fun shouldCheckItIsNotExpired() {
        assertThat(purchase.isExpired()).isFalse
    }

}