package com.mr3y.podcastindex

import com.mr3y.podcastindex.model.ValidationException
import io.ktor.client.engine.HttpClientEngineFactory

@DslMarker
annotation class PodcastIndexConfigDsl

@PodcastIndexConfigDsl
class PodcastIndexClientConfig {

    var engineFactory: HttpClientEngineFactory<*> = defaultHttpClientEngineFactory()

    var maxRetries: Int = 3

    var enableLogging: Boolean = false

    var loggingTag: String = "PodcastIndexSDK"

    fun authentication(action: PodcastIndexAuthentication.() -> Unit) {
        PodcastIndexAuthentication.action()
    }
}

@PodcastIndexConfigDsl
object PodcastIndexAuthentication {
    var userAgent: String? = null
        set(value) {
            if (value.isNullOrBlank()) {
                throw ValidationException("User agent cannot be null or blank")
            } else {
                field = value
            }
        }

    var authKey: String? = null

    var authSecret: String? = null
}
