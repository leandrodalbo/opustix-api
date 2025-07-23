package com.ticketera.model

import com.ticketera.data.BannerData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BannerTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(BannerData.banner).isNotEqualTo(Banner())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(BannerData.banner.toString()).isEqualTo(
            "Banner(id=${BannerData.banner.id}, url=${BannerData.banner.imageUrl})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(BannerData.banner.hashCode()).isEqualTo(BannerData.banner.id.hashCode())
    }
}