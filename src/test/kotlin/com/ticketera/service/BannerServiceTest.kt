package com.ticketera.service

import com.ticketera.data.BannerData
import com.ticketera.data.EventData
import com.ticketera.exceptions.TicketeraException
import com.ticketera.repositories.BannerRepository
import com.ticketera.repositories.EventRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.just
import io.mockk.runs
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectResponse
import java.util.Optional

class BannerServiceTest {

    private val s3Client: S3Client = mockk()

    private val eventRepository: EventRepository = mockk()
    private val bannerRepository: BannerRepository = mockk()

    private val bannerService = BannerService(
        s3Client,
        "banners-bucket",
        eventRepository,
        bannerRepository
    )

    @Test
    fun shouldUploadAndSaveABanner() {
        every { eventRepository.findById(any()) } returns Optional.of(EventData.event)
        every { bannerRepository.save(any()) } returns BannerData.banner
        every { s3Client.putObject(any<PutObjectRequest>(), any<RequestBody>()) } returns PutObjectResponse.builder()
            .eTag("mock-etag").build()


        val saved = bannerService.uploadBanner(BannerData.multipartFile, EventData.event.id, true, false, false)

        assertThat(saved).isEqualTo(BannerData.banner)

        verify { eventRepository.findById(any()) }
        verify { bannerRepository.save(any()) }
        verify { s3Client.putObject(any<PutObjectRequest>(), any<RequestBody>()) }
    }


    @Test
    fun shouldNotSaveABannerWithoutAnEvent() {
        every { eventRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                bannerService.uploadBanner(BannerData.multipartFile, EventData.event.id, true, false, false)
            }

        verify { eventRepository.findById(any()) }
    }


    @Test
    fun shouldDeleteABanner() {
        every { bannerRepository.findById(any()) } returns Optional.of(BannerData.banner)
        every { bannerRepository.deleteById(any()) } just runs
        every {
            s3Client.deleteObject(any<DeleteObjectRequest>())
        } returns DeleteObjectResponse.builder().build()

        bannerService.deleteBanner(BannerData.banner.id)


        verify { bannerRepository.findById(any()) }
        verify { bannerRepository.deleteById(any()) }
        verify { s3Client.deleteObject(any<DeleteObjectRequest>()) }
    }


    @Test
    fun deleteShouldFailWithAnInvalidId() {
        every { bannerRepository.findById(any()) } returns Optional.empty()

        assertThatExceptionOfType(TicketeraException::class.java)
            .isThrownBy {
                bannerService.deleteBanner(BannerData.banner.id)
            }

        verify { bannerRepository.findById(any()) }
    }
}