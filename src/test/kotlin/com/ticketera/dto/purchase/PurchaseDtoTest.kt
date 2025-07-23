package com.ticketera.dto.purchase

import com.ticketera.data.PurchaseReservationData
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class PurchaseDtoTest {

    @Test
    fun shouldCreateANewPurchaseDtoFromEntity() {
        val dto = PurchaseDto.fromEntities(
            PurchaseReservationData.purchase, listOf(PurchaseReservationData.reservation)
        )

        assertThat(dto).isInstanceOf(PurchaseDto::class.java)
        assertThat(dto.id).isEqualTo(PurchaseReservationData.purchase.id)
        assertThat(dto.userInfo).isEqualTo(PurchaseReservationData.purchase.userInfo)
        assertThat(dto.totalPrice).isEqualTo(PurchaseReservationData.purchase.totalPrice)
        assertThat(dto.paymentStatus).isEqualTo(PurchaseReservationData.purchase.paymentStatus)
        assertThat(dto.expiresAt).isEqualTo(PurchaseReservationData.purchase.expiresAt)
        assertThat(dto.reservations).isNotEmpty
    }

}