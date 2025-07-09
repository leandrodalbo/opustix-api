package com.ticketera.dto.purchase

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class NewPurchaseDtoTest {

    @Test
    fun shouldCreateANewPurchaseDtoFromEntity() {
        val dto = NewPurchaseDto.fromEntities(
            TestData.purchase, listOf(TestData.reservation)
        )

        assertThat(dto).isInstanceOf(NewPurchaseDto::class.java)
        assertThat(dto.id).isEqualTo(TestData.purchase.id)
        assertThat(dto.userInfo).isEqualTo(TestData.purchase.userInfo)
        assertThat(dto.totalPrice).isEqualTo(TestData.purchase.totalPrice)
        assertThat(dto.paymentStatus).isEqualTo(TestData.purchase.paymentStatus)
        assertThat(dto.expiresAt).isEqualTo(TestData.purchase.expiresAt)
        assertThat(dto.createdAt).isEqualTo(TestData.purchase.createdAt)
        assertThat(dto.reservations).hasSize(1)
        assertThat(dto.reservations.first()).isEqualTo(TestData.reservation)
    }

}