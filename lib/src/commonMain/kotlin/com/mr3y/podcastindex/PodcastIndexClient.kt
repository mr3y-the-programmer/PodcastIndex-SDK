package com.mr3y.podcastindex

import com.mr3y.podcastindex.extensions.installAuthenticationPlugin
import com.mr3y.podcastindex.extensions.installRetryPlugin
import com.mr3y.podcastindex.extensions.installSerializationPlugin
import com.mr3y.podcastindex.services.Misc
import com.mr3y.podcastindex.services.Podcasts
import com.mr3y.podcastindex.services.Search
import io.ktor.client.HttpClient

fun PodcastIndexClient(block: PodcastIndexClientConfig.() -> Unit): PodcastIndexClient {
    val config = PodcastIndexClientConfig().apply { block() }
    return PodcastIndexClient(config)
}

class PodcastIndexClient internal constructor(private val config: PodcastIndexClientConfig) {

    private val client: HttpClient = HttpClient(config.engineFactory) {
        installAuthenticationPlugin()
        installRetryPlugin(maxRetries = config.maxRetries)
        installSerializationPlugin()
    }

    val podcasts = Podcasts(client)

    val search = Search(client)

    val misc = Misc(client)

    companion object {
        internal const val BaseUrl = "https://api.podcastindex.org/api/1.0"
    }
}
