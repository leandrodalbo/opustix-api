package com.ticketera.service

import com.ticketera.exceptions.ErrorMessage
import com.ticketera.exceptions.TicketeraException
import com.ticketera.model.Banner
import com.ticketera.repositories.BannerRepository
import com.ticketera.repositories.EventRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.time.Instant
import java.util.UUID

@Service
class BannerService(
    private val s3Client: S3Client,
    @Value("\${aws.bucket}")
    private val bucketName: String,
    private val eventRepository: EventRepository,
    private val bannerRepository: BannerRepository

) {

    fun uploadBanner(
        file: MultipartFile,
        eventId: UUID,
        isMain: Boolean,
        isSecond: Boolean,
        isAdditional: Boolean
    ): Banner {
        val event = eventRepository.findById(eventId).orElseThrow { TicketeraException(ErrorMessage.EVENT_NOT_FOUND) }

        val bannerId = UUID.randomUUID()
        val key = "banners/${bannerId}_${file.originalFilename}"

        s3Client.putObject(
            PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.contentType)
                .build(),
            RequestBody.fromBytes(file.bytes)
        )

        val imageUrl = "https://${bucketName}.s3.amazonaws.com/$key"

        val banner = Banner(
            id = bannerId,
            imageUrl = imageUrl,
            isMain = isMain,
            isSecond = isSecond,
            isAdditional = isAdditional,
            createdAt = Instant.now().toEpochMilli(),
            event = event
        )

        return bannerRepository.save(banner)
    }


    fun deleteBanner(bannerId: UUID) {
        val banner =
            bannerRepository.findById(bannerId).orElseThrow { TicketeraException(ErrorMessage.BANNER_NOT_FOUND) }

        banner.imageUrl?.let {
            val key = it.substringAfter(".com/")
            s3Client.deleteObject(
                DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build()
            )

        } ?: throw TicketeraException(ErrorMessage.BANNER_NOT_FOUND)

        bannerRepository.deleteById(bannerId)
    }
}