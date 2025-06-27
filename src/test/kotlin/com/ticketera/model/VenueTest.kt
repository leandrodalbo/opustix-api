package com.ticketera.model

import com.ticketera.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VenueTest : TestData() {

    @Test
    fun shouldOverrideEquals() {
        assertThat(venue).isEqualTo(venue.copy())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(venue.toString()).isEqualTo(
            "Venue(id=${venue.id}, name=${venue.name}, createdAt=${venue.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(venue.hashCode()).isEqualTo(venue.copy().hashCode())
    }
}