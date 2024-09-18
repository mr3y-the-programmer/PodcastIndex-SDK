package com.mr3y.podcastindex

@DslMarker
internal annotation class PodcastIndexConfigDsl

/**
 * Configuration for [PodcastIndexClient] with sensible defaults.
 */
@PodcastIndexConfigDsl
class PodcastIndexClientConfig {

    /**
     * Maximum number of retries for failed requests before giving up. Default is 3.
     */
    var maxRetries: Int = 3

    /**
     * Whether to enable or disable logging, useful for debugging purposes. Default is false.
     */
    var enableLogging: Boolean = false

    /**
     * The tag to use for logging, useful for filtering logs. Default is "PodcastIndexSDK".
     */
    var loggingTag: String = "PodcastIndexSDK"

    /**
     * Whether to enable or disable timeout. Default is true.
     */
    var enableTimeout: Boolean = true

    /**
     * The default timeout for requests in milliseconds. Default is 10_000.
     */
    var defaultTimeout: Long = 10_000
}

internal data class Authentication(
    val key: String,
    val secret: String,
    val userAgent: String
)
