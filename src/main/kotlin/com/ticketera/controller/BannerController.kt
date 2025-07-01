package com.ticketera.controller

import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Banner
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.BannerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/ticketera/banners")
class BannerController(
    private val headersService: AuthHeadersService,
    private val bannerService: BannerService
) {

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadBanner(
        @RequestHeader
        headers: Map<String, String>,
        @RequestParam file: MultipartFile,
        @RequestParam eventId: UUID,
        @RequestParam(required = false, defaultValue = "false") isMain: Boolean,
        @RequestParam(required = false, defaultValue = "false") isSecond: Boolean,
        @RequestParam(required = false, defaultValue = "false") isAdditional: Boolean
    ): Banner {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)

        return bannerService.uploadBanner(file, eventId, isMain, isSecond, isAdditional)
    }

    @DeleteMapping("/delete/{bannerId}")
    fun deleteBanner(
        @RequestHeader
        headers: Map<String, String>,
        @PathVariable bannerId: UUID
    ) {
        if (!headersService.isAdminOrOrganizer(headers)) throw TicketeraException(ErrorMessage.INVALID_REQUEST)
        bannerService.deleteBanner(bannerId)
    }

}