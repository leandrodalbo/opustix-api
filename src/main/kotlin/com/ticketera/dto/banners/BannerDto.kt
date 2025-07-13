package com.ticketera.dto.banners

import com.ticketera.model.Banner
import java.util.UUID

data class BannerDto(
    val id: UUID,
    val imageUrl: String? = null,
    val isMain: Boolean = false,
    val isSecond: Boolean = false,
    val isAdditional: Boolean = false
) {
    companion object {
        fun fromEntity(banner: Banner) =
            BannerDto(banner.id, banner.imageUrl, banner.isMain, banner.isSecond, banner.isAdditional)
    }
}