package com.ticketera.dto.venues

import com.ticketera.TestData
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class VenueDtoTest {

    @Test
    fun shouldCreateADtoFromEntity() {
        val dto = VenueDto.fromEntity(
            TestData.venue
        )
        assertThat(dto).isEqualTo(TestData.venueDto)

    }
}