package com.ticketera.dto.purchase

import com.ticketera.dto.reservation.ReservationDto
import com.ticketera.model.PaymentStatus
import com.ticketera.model.Purchase
import com.ticketera.model.Reservation
import java.util.UUID

data class PurchaseDto(
    val id: UUID,
    val userInfo: String,
    val totalPrice: Double,
    val paymentStatus: PaymentStatus,
    val expiresAt: Long? = null,
    val reservations: List<ReservationDto>
) {
    companion object {
        fun fromEntities(purchase: Purchase, reservations: List<Reservation>): PurchaseDto {
            return PurchaseDto(
                id = purchase.id,
                userInfo = purchase.userInfo,
                totalPrice = purchase.totalPrice,
                paymentStatus = purchase.paymentStatus,
                expiresAt = purchase.expiresAt,
                reservations = reservations.map { reservation ->
                    ReservationDto.fromEntity(reservation)
                }
            )
        }
    }
}
