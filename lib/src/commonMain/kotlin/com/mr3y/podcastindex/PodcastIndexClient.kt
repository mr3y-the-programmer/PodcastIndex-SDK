package com.mr3y.podcastindex

import com.mr3y.podcastindex.extensions.installAuthenticationPlugin
import com.mr3y.podcastindex.extensions.installLoggingPlugin
import com.mr3y.podcastindex.extensions.installRetryPlugin
import com.mr3y.podcastindex.extensions.installSerializationPlugin
import com.mr3y.podcastindex.extensions.installTimeoutPlugin
import com.mr3y.podcastindex.services.Episodes
import com.mr3y.podcastindex.services.Misc
import com.mr3y.podcastindex.services.Podcasts
import com.mr3y.podcastindex.services.Search
import io.ktor.client.HttpClient

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
fun PodcastIndexClient(
    authKey: String,
    authSecret: String,
    userAgent: String,
    block: PodcastIndexClientConfig.() -> Unit = {}
): PodcastIndexClient {
    require(userAgent.isNotBlank()) { "User agent cannot be blank" }
    val auth = Authentication(authKey, authSecret, userAgent)
    val config = PodcastIndexClientConfig().apply { block() }
    return PodcastIndexClient(auth, config)
}

/**
 * PodcastIndexClient instance responsible for interacting with different PodcastIndex API endpoints.
 */
class PodcastIndexClient internal constructor(
    private val authentication: Authentication,
    private val config: PodcastIndexClientConfig
) {

    private val client: HttpClient = HttpClient(defaultHttpClientEngineFactory()) {
        installAuthenticationPlugin(authentication)
        installRetryPlugin(authentication, maxRetries = config.maxRetries)
        installSerializationPlugin()
        if (config.enableLogging) {
            installLoggingPlugin(config.loggingTag)
        }
        if (config.enableTimeout) {
            installTimeoutPlugin(config.defaultTimeout)
        }
    }

    val podcasts = Podcasts(client)

    val search = Search(client)

    val episodes = Episodes(client)

    val misc = Misc(client)

    companion object {
        internal const val BaseUrl = "https://api.podcastindex.org/api/1.0"
    }
}
