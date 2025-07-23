package com.ticketera.conf

import com.ticketera.data.BannerData
import com.ticketera.data.EventData
import com.ticketera.data.EventSeatData
import com.ticketera.data.EventSectorData
import com.ticketera.data.PurchaseReservationData
import com.ticketera.data.TicketTypeData
import com.ticketera.data.VenueData
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.PostgreSQLContainer

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
abstract class TestContainerConf {

    @Autowired
    private lateinit var entityManager: TestEntityManager


    companion object {
        val postgres = PostgreSQLContainer<Nothing>("postgres:15")
            .apply {
                withDatabaseName("ticketera")
                withUsername("ticketera")
                withPassword("dGVzdGRi")
                withReuse(true)
            }

        fun registerProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.flyway.url", postgres::getJdbcUrl)
            registry.add("spring.flyway.user", postgres::getUsername)
            registry.add("spring.flyway.password", postgres::getPassword)
            registry.add("spring.flyway.baseline-on-migrate", { true })

        }
    }

    init {
        postgres.start()
    }

    @BeforeEach
    fun setup() {

        entityManager.persist(VenueData.venue)
        entityManager.persist(EventData.event)
        entityManager.persist(BannerData.banner)
        entityManager.persist(TicketTypeData.ticketType)
        entityManager.persist(EventSectorData.eventSector)
        entityManager.persist(EventSeatData.eventSeat)
        entityManager.persist(PurchaseReservationData.purchase)
        entityManager.persist(PurchaseReservationData.reservation)
        entityManager.flush()
        entityManager.clear()
    }
}