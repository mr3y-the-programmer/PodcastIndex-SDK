package com.mr3y.podcastindex

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

@DslMarker
@InternalPodcastIndexApi
public annotation class PodcastIndexConfigDsl

/**
 * Configuration for [PodcastIndexClient] with sensible defaults.
 */
@PodcastIndexConfigDsl
public class PodcastIndexClientConfig {

    internal var httpClientBuilder: () -> HttpClient = { HttpClient(defaultHttpClientEngineFactory()) }

    /**
     * Maximum number of retries for failed requests before giving up. Default is 3.
     */
    public var maxRetries: Int = 3

    /**
     * Whether to enable or disable logging, useful for debugging purposes. Default is false.
     */
    public var enableLogging: Boolean = false

    /**
     * The tag to use for logging, useful for filtering logs. Default is "PodcastIndexSDK".
     */
    public var loggingTag: String = "PodcastIndexSDK"

    /**
     * Whether to enable or disable timeout. Default is true.
     */
    public var enableTimeout: Boolean = true

    /**
     * The default timeout for requests in milliseconds. Default is 10_000.
     */
    public var defaultTimeout: Long = 10_000

    /**
     * Creates an custom [HttpClient] with the specified [HttpClientEngineFactory] and optional
     * [block] configuration.
     *
     * Note that [block] configuration doesn't replace the existing default configuration added by
     * this library. It will be applied on top of it.
     */
    public fun <T : HttpClientEngineConfig> httpClient(
        engineFactory: HttpClientEngineFactory<T>,
        block: HttpClientConfig<T>.() -> Unit = {}
    ) {
        httpClientBuilder = {
            HttpClient(engineFactory, block)
        }
    }
}
