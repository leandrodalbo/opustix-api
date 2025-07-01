package com.ticketera.props

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws")
data class AWSProps(
    val accessKey: String,
    val secretKey: String,
    val region: String,
    val bucket: String
)
