package com.ticketera.dto.venues

import com.ticketera.data.VenueData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class VenueDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = VenueDto.fromEntity(
            VenueData.venue
        )
        assertThat(dto).isEqualTo(VenueData.venueDto)

    }
}