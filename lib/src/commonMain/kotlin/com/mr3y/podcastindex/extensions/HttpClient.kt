package com.mr3y.podcastindex.extensions

import co.touchlab.kermit.Logger as KermitLogger
import com.mr3y.podcastindex.PodcastIndexAuthentication
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.sha1
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

internal fun HttpClientConfig<*>.installAuthenticationPlugin() {
    // Add the necessary authentication headers to every request going out to PodcastIndex endpoints
    val requestAuthenticationPlugin = createClientPlugin("requestAuthenticatorPlugin") {
        onRequest { request, _ ->
            request.headers {
                val epoch = (Clock.System.now().toEpochMilliseconds() / 1000)
                val key = PodcastIndexAuthentication.authKey ?: reportUserIsUnAuthenticated()
                val secret = PodcastIndexAuthentication.authSecret ?: reportUserIsUnAuthenticated()
                append("User-Agent", PodcastIndexAuthentication.userAgent ?: reportUserIsUnAuthenticated())
                append("X-Auth-Date", epoch.toString())
                append("X-Auth-Key", key)
                append("Authorization", authHeader(epoch, key, secret))
            }
        }
    }
    install(requestAuthenticationPlugin)
}

internal fun HttpClientConfig<*>.installRetryPlugin(maxRetries: Int) {
    install(HttpRequestRetry) {
        modifyRequest { request ->
            // The server expects the auth date to be within a 3 minutes time window around the server time
            // but sometimes the request time/auth date is off by a few seconds/milliseconds, therefore,
            // to solve this, retry the request with a 1.5 minutes offset.
            val epoch = (Clock.System.now().toEpochMilliseconds() / 1000) - 150
            request.headers["X-Auth-Date"] = epoch.toString()
            val key = PodcastIndexAuthentication.authKey ?: reportUserIsUnAuthenticated()
            val secret = PodcastIndexAuthentication.authSecret ?: reportUserIsUnAuthenticated()
            request.headers["Authorization"] = authHeader(epoch, key, secret)
        }
        retryIf(maxRetries) { _, httpResponse ->
            when {
                httpResponse.status.value in 500..599 -> true
                httpResponse.status == HttpStatusCode.TooManyRequests -> true
                httpResponse.status == HttpStatusCode.Unauthorized -> true
                else -> false
            }
        }
        constantDelay()
    }
}

internal fun HttpClientConfig<*>.installLoggingPlugin(tag: String) {
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                KermitLogger.d(tag = tag, messageString = message)
            }
        }
        sanitizeHeader { header ->
            header == "Authorization" || header == "X-Auth-Key"
        }
    }
}

internal fun HttpClientConfig<*>.installSerializationPlugin() {
    val jsonInstance = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    install(ContentNegotiation) {
        json(jsonInstance)
    }
}

private fun reportUserIsUnAuthenticated(): Nothing {
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
           ```
           Check https://podcastindex-org.github.io/docs-api/#auth for more details
        """.trimIndent()
    )
}

private fun authHeader(epoch: Long, key: String, secret: String): String {
    val authHash = sha1("$key$secret$epoch".toByteArray())
    return byteToHex(authHash)
}

@OptIn(ExperimentalStdlibApi::class)
private fun byteToHex(binary: ByteArray): String {
    val format = HexFormat {
        bytes {
            groupSeparator = ""
            bytesPerGroup = 1
        }
    }
    return binary.toHexString(format)
}
