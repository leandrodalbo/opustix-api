import org.springframework.test.context.DynamicPropertyRegistry
import org.testcontainers.containers.PostgreSQLContainer

class TestContainerConf {
    companion object {
        val postgres = PostgreSQLContainer<Nothing>("postgres:16.4")
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
        }
    }
}