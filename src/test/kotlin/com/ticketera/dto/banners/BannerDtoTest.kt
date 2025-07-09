package com.ticketera.dto.banners

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class BannerDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = BannerDto.fromEntity(
            TestData.banner
        )
        assertThat(dto).isEqualTo(TestData.bannerDto)

    }
}