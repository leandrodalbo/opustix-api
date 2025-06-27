package com.ticketera.repositories

import com.ticketera.TestData
import com.ticketera.model.Purchase
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
final class PurchaseRepositoryTest : TestData() {
    @Autowired
    private lateinit var purchaseRepository: PurchaseRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager


    @BeforeEach
    fun setup() {
        entityManager.persist(venue)
        entityManager.persist(event)
        entityManager.persist(ticketType)
        entityManager.persist(eventSector)
        entityManager.persist(eventSeat)
        entityManager.persist(purchase)
        entityManager.persist(reservation)
        entityManager.flush()
        entityManager.clear()
    }


    @Test
    fun shouldFindByIdWithReservations() {
        val result = purchaseRepository.findById(purchaseId).get()

        assertThat(result).isInstanceOfAny(Purchase::class.java)
        assertThat(result.reservations.toList()).isEqualTo(listOf(reservation))
    }


    companion object {
        @Container
        val postgres = TestContainerConf.postgres

        @JvmStatic
        @DynamicPropertySource
        fun registerProperties(registry: DynamicPropertyRegistry) =
            TestContainerConf.registerProperties(registry)
    }
}