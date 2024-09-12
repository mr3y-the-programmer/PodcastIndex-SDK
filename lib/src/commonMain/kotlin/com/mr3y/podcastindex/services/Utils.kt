package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.Authentication
import com.mr3y.podcastindex.model.Response

internal inline fun withErrorHandling(block: () -> Response): Response {
    checkIsAuthenticated()
    return try {
        block()
    } catch (ex: Exception) {
        Response.UnknownError(ex.message)
    }
}

internal fun checkIsAuthenticated() {
    val agent = Authentication.userAgent
    val key = Authentication.authKey
    val secret = Authentication.authSecret
    if (agent.isNullOrBlank() || key.isNullOrBlank() || secret.isNullOrBlank()) {
        throw IllegalArgumentException(
            """
                    You're Unauthenticated! Provide your authentication credentials obtained from https://api.podcastindex.org/ in the client initialization block
                    ```
                    val client = PodcastIndexClient {
                        authentication {
                            userAgent = "<your-user-agent>"
                            authKey = "<your-auth-key>"
                            authSecret = "<your-auth-secret>"
                        }
                    }
                    Check https://podcastindex-org.github.io/docs-api/#auth for more details
                    ```
                """.trimIndent()
        )
    }
}

