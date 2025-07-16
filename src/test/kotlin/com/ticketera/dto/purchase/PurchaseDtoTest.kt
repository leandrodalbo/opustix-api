package com.ticketera.dto.purchase

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class PurchaseDtoTest {

    @Test
    fun shouldCreateANewPurchaseDtoFromEntity() {
        val dto = PurchaseDto.fromEntities(
            TestData.purchase, listOf(TestData.reservation)
        )

        assertThat(dto).isInstanceOf(PurchaseDto::class.java)
        assertThat(dto.id).isEqualTo(TestData.purchase.id)
        assertThat(dto.userInfo).isEqualTo(TestData.purchase.userInfo)
        assertThat(dto.totalPrice).isEqualTo(TestData.purchase.totalPrice)
        assertThat(dto.paymentStatus).isEqualTo(TestData.purchase.paymentStatus)
        assertThat(dto.expiresAt).isEqualTo(TestData.purchase.expiresAt)
        assertThat(dto.reservations).isNotEmpty
    }

}