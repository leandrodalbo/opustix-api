package com.ticketera.dto.purchase

import com.ticketera.model.PaymentStatus
import com.ticketera.model.Purchase
import com.ticketera.model.Reservation
import java.util.UUID

data class NewPurchaseDto(
    val id: UUID,
    val userInfo: String,
    val totalPrice: Double,
    val paymentStatus: PaymentStatus,
    val expiresAt: Long? = null,
    val createdAt: Long,
    val reservations: List<Reservation>
) {
    companion object {
        fun fromEntities(purchase: Purchase, reservations: List<Reservation>): NewPurchaseDto {
            return NewPurchaseDto(
                id = purchase.id,
                userInfo = purchase.userInfo,
                totalPrice = purchase.totalPrice,
                paymentStatus = purchase.paymentStatus,
                expiresAt = purchase.expiresAt,
                createdAt = purchase.createdAt,
                reservations = reservations
            )
        }
    }
}
