package com.ticketera

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

        entityManager.persist(TestData.venue)
        entityManager.persist(TestData.event)
        entityManager.persist(TestData.banner)
        entityManager.persist(TestData.ticketType)
        entityManager.persist(TestData.eventSector)
        entityManager.persist(TestData.eventSeat)
        entityManager.persist(TestData.purchase)
        entityManager.persist(TestData.reservation)
        entityManager.flush()
        entityManager.clear()
    }
}