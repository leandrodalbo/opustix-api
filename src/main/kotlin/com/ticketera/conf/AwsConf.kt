package com.ticketera.conf

import com.ticketera.props.AWSProps

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@Configuration
class AwsConf {

    @Bean
    fun s3Client(awsProps: AWSProps): S3Client {
        val credentials = AwsBasicCredentials.create(
            awsProps.accessKey,
            awsProps.secretKey
        )

        return S3Client.builder()
            .region(Region.of(awsProps.region))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .build()
    }
}