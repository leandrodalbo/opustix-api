package com.ticketera.dto.banners

import com.ticketera.data.BannerData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class BannerDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = BannerDto.fromEntity(
            BannerData.banner
        )
        assertThat(dto).isEqualTo(BannerData.bannerDto)
    }
}