package com.mr3y.podcastindex.ktor2

import com.mr3y.podcastindex.Authentication
import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.PodcastIndexClientConfig
import com.mr3y.podcastindex.PodcastIndexConfigDsl

/**
 * Creates a new [PodcastIndexClient] instance.
 * This is the entry point to the SDK, call this with your Authentication credentials to create a client instance and start interacting with the API.
 *
 * @param authKey Your PodcastIndex API Key.
 * @param authSecret Your PodcastIndex API Secret.
 * @param userAgent The User Agent identifying the system/product you are using to make requests.
 * @param block optional configuration block to configure additional behavior like timeout, logging..etc, see [PodcastIndexClientConfig].
 *
 * @return [PodcastIndexClient]
 */
@PodcastIndexConfigDsl
public fun PodcastIndexClient(
    authKey: String,
    authSecret: String,
    userAgent: String,
    block: PodcastIndexClientConfig.() -> Unit = {},
): PodcastIndexClient {
    require(userAgent.isNotBlank()) { "User agent cannot be blank" }
    val auth = Authentication(authKey, authSecret, userAgent)
    val config = PodcastIndexClientConfig().apply { block() }
    return PodcastIndexClient(
        httpClientConfig = {
            installAuthenticationPlugin(auth)
            installRetryPlugin(auth, maxRetries = config.maxRetries)
            installSerializationPlugin()
            if (config.enableLogging) {
                installLoggingPlugin(config.loggingTag)
            }
            if (config.enableTimeout) {
                installTimeoutPlugin(config.defaultTimeout)
            }
        }
    )
}
