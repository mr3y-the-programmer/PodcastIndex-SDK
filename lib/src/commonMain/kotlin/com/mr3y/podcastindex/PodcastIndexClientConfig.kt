package com.mr3y.podcastindex

@DslMarker
annotation class PodcastIndexConfigDsl

@PodcastIndexConfigDsl
class PodcastIndexClientConfig {

    var maxRetries: Int = 3

    var enableLogging: Boolean = false

    var loggingTag: String = "PodcastIndexSDK"

    var enableTimeout: Boolean = true

    var defaultTimeout: Long = 10_000
}

internal data class Authentication(
    val key: String,
    val secret: String,
    val userAgent: String
)
