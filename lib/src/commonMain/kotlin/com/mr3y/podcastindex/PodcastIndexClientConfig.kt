package com.mr3y.podcastindex

import io.ktor.client.engine.HttpClientEngineFactory

class PodcastIndexClientConfig {

    var engineFactory: HttpClientEngineFactory<*> = defaultHttpClientEngineFactory()

    var maxRetries: Int = 3

    fun authentication(action: PodcastIndexAuthentication.() -> Unit) {
        PodcastIndexAuthentication.action()
    }
}

object PodcastIndexAuthentication {
    var userAgent: String? = null

    var authKey: String? = null

    var authSecret: String? = null
}
