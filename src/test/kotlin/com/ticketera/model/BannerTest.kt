package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BannerTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.banner).isNotEqualTo(Banner())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.banner.toString()).isEqualTo(
            "Banner(id=${TestData.banner.id}, url=${TestData.banner.imageUrl})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.banner.hashCode()).isEqualTo(TestData.banner.id.hashCode())
    }
}