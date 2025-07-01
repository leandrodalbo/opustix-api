package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BannerTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(banner).isEqualTo(banner.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(banner.toString()).isEqualTo(
            "Banner(id=${banner.id}, url=${banner.imageUrl})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(venue.hashCode()).isEqualTo(venue.copy().hashCode())
    }


}