package com.ticketera.ticketera


import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container


abstract class ContainerTests {

    companion object {

        @Container
        val postgres = PostgreSQLContainer<Nothing>("postgres:16.4")
            .apply {
                withDatabaseName("ticketera")
                withUsername("ticketera")
                withPassword("dGVzdGRi")

            }.also { it.withReuse(true) }

        @JvmStatic
        @DynamicPropertySource
        fun overrideProps(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }
    }
}
