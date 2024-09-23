package com.mr3y.podcastindex.extensions

import com.mr3y.podcastindex.Authentication
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.sha1
import io.ktor.utils.io.core.toByteArray
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import co.touchlab.kermit.Logger as KermitLogger

internal fun HttpClientConfig<*>.installAuthenticationPlugin(authenticationInfo: Authentication) {
    // Add the necessary authentication headers to every request going out to PodcastIndex endpoints
    val requestAuthenticationPlugin = createClientPlugin("requestAuthenticatorPlugin") {
        onRequest { request, _ ->
            request.headers {
                val epoch = (Clock.System.now().toEpochMilliseconds() / 1000)
                append("User-Agent", authenticationInfo.userAgent)
                append("X-Auth-Date", epoch.toString())
                append("X-Auth-Key", authenticationInfo.key)
                append("Authorization", authHeader(epoch, authenticationInfo.key, authenticationInfo.secret))
            }
        }
    }
    install(requestAuthenticationPlugin)
}

internal fun HttpClientConfig<*>.installRetryPlugin(authenticationInfo: Authentication, maxRetries: Int) {
    install(HttpRequestRetry) {
        modifyRequest { request ->
            // The server expects the auth date to be within a 3 minutes time window around the server time
            // but sometimes the request time/auth date is off by a few seconds/milliseconds, therefore,
            // to solve this, retry the request with a 1.5 minutes offset.
            val epoch = (Clock.System.now().toEpochMilliseconds() / 1000) - 150
            request.headers["X-Auth-Date"] = epoch.toString()
            request.headers["Authorization"] = authHeader(epoch, authenticationInfo.key, authenticationInfo.secret)
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

internal fun HttpClientConfig<*>.installTimeoutPlugin(reqTimeout: Long) {
    install(HttpTimeout) {
        requestTimeoutMillis = reqTimeout
    }
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
