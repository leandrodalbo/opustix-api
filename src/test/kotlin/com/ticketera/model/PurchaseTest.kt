package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PurchaseTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.purchase).isNotEqualTo(Purchase())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.purchase.toString()).isEqualTo(
            "Purchase(id=${TestData.purchase.id}, userInfo=${TestData.purchase.userInfo}, totalPrice=${TestData.purchase.totalPrice}, paymentStatus=${TestData.purchase.paymentStatus}, expiresAt=${TestData.purchase.expiresAt}, createdAt=${TestData.purchase.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.purchase.hashCode()).isEqualTo(TestData.purchase.id.hashCode())
    }

    @Test
    fun shouldCheckItIsNotExpired() {
        assertThat(TestData.purchase.isExpired()).isFalse
    }
}