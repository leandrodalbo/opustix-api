package com.ticketera.model

import com.ticketera.data.VenueData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VenueTest {

    @Test
    fun shouldOverrideEquals() {
        assertThat(VenueData.venue).isNotEqualTo(Venue())
    }

    @Test
    fun shouldOverrideToString() {
        assertThat(VenueData.venue.toString()).isEqualTo(
            "Venue(id=${VenueData.venue.id}, name=${VenueData.venue.name}, createdAt=${VenueData.venue.createdAt})"
        )
    }

    @Test
    fun shouldOverrideHashcode() {
        assertThat(VenueData.venue.hashCode()).isEqualTo(VenueData.venue.id.hashCode())
    }
}