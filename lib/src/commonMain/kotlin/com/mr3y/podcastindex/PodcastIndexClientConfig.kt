package com.mr3y.podcastindex

import io.ktor.client.engine.HttpClientEngineFactory

@DslMarker
annotation class PodcastIndexConfigDsl

@PodcastIndexConfigDsl
class PodcastIndexClientConfig {

    var engineFactory: HttpClientEngineFactory<*> = defaultHttpClientEngineFactory()

    var maxRetries: Int = 3

    fun authentication(action: PodcastIndexAuthentication.() -> Unit) {
        PodcastIndexAuthentication.action()
    }
}

@PodcastIndexConfigDsl
object PodcastIndexAuthentication {
    var userAgent: String? = null

    var authKey: String? = null

    var authSecret: String? = null
}
