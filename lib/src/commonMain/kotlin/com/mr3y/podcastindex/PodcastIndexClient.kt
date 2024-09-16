package com.mr3y.podcastindex

import com.mr3y.podcastindex.extensions.installAuthenticationPlugin
import com.mr3y.podcastindex.extensions.installLoggingPlugin
import com.mr3y.podcastindex.extensions.installRetryPlugin
import com.mr3y.podcastindex.extensions.installSerializationPlugin
import com.mr3y.podcastindex.extensions.installTimeoutPlugin
import com.mr3y.podcastindex.services.Misc
import com.mr3y.podcastindex.services.Podcasts
import com.mr3y.podcastindex.services.Search
import io.ktor.client.HttpClient

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

    val misc = Misc(client)

    companion object {
        internal const val BaseUrl = "https://api.podcastindex.org/api/1.0"
    }
}
