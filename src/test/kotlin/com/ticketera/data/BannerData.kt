package com.ticketera.data

import com.ticketera.dto.banners.BannerDto
import com.ticketera.model.Banner
import org.springframework.mock.web.MockMultipartFile
import java.time.Instant
import java.util.UUID

class BannerData {
    companion object {

        private val imageBytes = javaClass.getResourceAsStream("/banner.jpg").readBytes()

        val multipartFile = MockMultipartFile("file", "test-image.jpg", "image/jpeg", imageBytes)

        val bannerImageUrl = "https://s3.banner.img?id=${UUID.randomUUID()}"

        val banner = Banner(
            UUID.randomUUID(),
            bannerImageUrl,
            true,
            false,
            false,
            Instant.now().toEpochMilli(),
            EventData.event
        )

        val bannerDto = BannerDto.Companion.fromEntity(banner)
    }
}