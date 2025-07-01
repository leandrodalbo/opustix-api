package com.ticketera.repositories

import com.ticketera.TestData
import com.ticketera.model.Banner
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
final class BannerRepositoryTest : TestData() {
    @Autowired
    private lateinit var bannerRepository: BannerRepository

    @Autowired
    private lateinit var entityManager: TestEntityManager


    @BeforeEach
    fun setup() {
        entityManager.persistAndFlush(venue)
        entityManager.persistAndFlush(event)
        entityManager.persistAndFlush(banner)
    }

    @Test
    fun shouldFindById() {
        assertThat(bannerRepository.findById(bannerId).get())
            .isInstanceOfAny(Banner::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(bannerRepository.findAll().map { it.id })
            .isEqualTo(listOf(bannerId))
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