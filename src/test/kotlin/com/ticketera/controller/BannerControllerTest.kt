package com.ticketera.controller

import com.ninjasquad.springmockk.MockkBean
import com.ticketera.TestData
import com.ticketera.service.AuthHeadersService
import com.ticketera.service.BannerService
import io.mockk.every
import io.mockk.verify
import io.mockk.just
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete

import org.junit.jupiter.api.Test

@WebMvcTest(BannerController::class)
class BannerControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @MockkBean
    private lateinit var bannerService: BannerService

    @MockkBean
    private lateinit var userAuthHeadersService: AuthHeadersService

    @Test
    fun shouldUploadABanner() {
        every { bannerService.uploadBanner(any(), TestData.event.id, true, false, false) } returns TestData.banner
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true

        val response = mvc.perform(
            multipart("/ticketera/banners/upload")
                .file(TestData.multipartFile)
                .param("eventId", TestData.event.id.toString())
                .param("isMain", "true")
                .headers(TestData.httpHeaders)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.CREATED.value())

        verify { bannerService.uploadBanner(any(), TestData.event.id, true, false, false) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }


    @Test
    fun shouldDeleteABanner() {
        every { bannerService.deleteBanner(any()) } just runs
        every { userAuthHeadersService.isAdminOrOrganizer(any()) } returns true
        val response = mvc.perform(
            delete("/ticketera/banners/delete/${TestData.banner.id}")
                .headers(TestData.httpHeaders)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().response

        assertThat(response.status).isEqualTo(HttpStatus.OK.value())

        verify { bannerService.deleteBanner(any()) }
        verify { userAuthHeadersService.isAdminOrOrganizer(any()) }
    }
}