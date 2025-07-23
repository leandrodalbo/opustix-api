package com.ticketera.repositories

import com.ticketera.conf.TestContainerConf
import com.ticketera.data.PurchaseReservationData
import com.ticketera.model.Purchase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final class PurchaseRepositoryTest : TestContainerConf() {

    @Autowired
    private lateinit var purchaseRepository: PurchaseRepository


    @Test
    fun shouldFindByIdWithReservations() {
        val result = purchaseRepository.findById(PurchaseReservationData.purchase.id).get()

        assertThat(result).isInstanceOfAny(Purchase::class.java)
        assertThat(result.reservations.toList()).isEqualTo(listOf(PurchaseReservationData.reservation))
    }

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}