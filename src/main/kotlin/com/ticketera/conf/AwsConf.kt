package com.ticketera.conf

import com.ticketera.props.AWSProps

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI

@Configuration
class AwsConf {

    @Bean
    fun s3Client(awsProps: AWSProps): S3Client {
        val credentials = AwsBasicCredentials.create(
            awsProps.accessKey,
            awsProps.secretKey
        )

        val builder = S3Client.builder()
            .region(Region.of(awsProps.region))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))

        return awsProps.endpoint?.let { builder.endpointOverride(URI.create(awsProps.endpoint)).build() }
            ?: builder.build()
    }
}