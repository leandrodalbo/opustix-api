package com.ticketera.repositories

import com.ticketera.TestContainerConf
import com.ticketera.TestData
import com.ticketera.model.Banner
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
final class BannerRepositoryTest : TestContainerConf() {
    @Autowired
    private lateinit var bannerRepository: BannerRepository


    @Test
    fun shouldFindById() {
        assertThat(bannerRepository.findById(TestData.banner.id).get())
            .isInstanceOfAny(Banner::class.java)
    }

    @Test
    fun shouldFindByAll() {
        assertThat(bannerRepository.findAll().map { it.id })
            .isEqualTo(listOf(TestData.banner.id))
    }


    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun dynamicProperties(registry: DynamicPropertyRegistry) {
            registerProperties(registry)
        }
    }
}