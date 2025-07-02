package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VenueTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(TestData.venue).isNotEqualTo(Venue())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(TestData.venue.toString()).isEqualTo(
            "Venue(id=${TestData.venue.id}, name=${TestData.venue.name}, createdAt=${TestData.venue.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(TestData.venue.hashCode()).isEqualTo(TestData.venue.id.hashCode())
    }
}