package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class Search internal constructor(private val client: HttpClient) {

    suspend fun forPodcastsByTerm(
        term: String,
        value: Value = Value.Any,
        limit: Int = 0,
        aponly: Boolean = false,
        returnCleanOnly: Boolean = false,
        includeSimilar: Boolean = false,
        includeFullText: Boolean = false
    ): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/search/byterm") {
                parameter("q", term)
                if (value != Value.Any) {
                    parameter("val", value.encodedValue)
                }
                if (limit > 0) {
                    parameter("max", limit)
                }
                if (aponly) {
                    parameter("aponly", true)
                }
                if (returnCleanOnly) {
                    parameter("clean", true)
                }
                if (includeSimilar) {
                    parameter("similar", true)
                }
                if (includeFullText) {
                    parameter("fulltext", true)
                }
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.SearchForPodcastsSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }

    suspend fun forPodcastsByTitle(
        title: String,
        value: Value = Value.Any,
        limit: Int = 0,
        returnCleanOnly: Boolean = false,
        includeSimilar: Boolean = false,
        includeFullText: Boolean = false
    ): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/search/bytitle") {
                parameter("q", title)
                if (value != Value.Any) {
                    parameter("val", value.encodedValue)
                }
                if (limit > 0) {
                    parameter("max", limit)
                }
                if (returnCleanOnly) {
                    parameter("clean", true)
                }
                if (includeSimilar) {
                    parameter("similar", true)
                }
                if (includeFullText) {
                    parameter("fulltext", true)
                }
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.SearchForPodcastsSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }

    suspend fun forEpisodesByPerson(
        name: String,
        limit: Int = 0,
        includeFullText: Boolean = false
    ): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/search/byperson") {
                parameter("q", name)
                if (limit > 0) {
                    parameter("max", limit)
                }
                if (includeFullText) {
                    parameter("fulltext", true)
                }
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.SearchForEpisodesSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }

    suspend fun forMusicPodcastsByTerm(
        term: String,
        value: Value = Value.Any,
        limit: Int = 0,
        aponly: Boolean = false,
        returnCleanOnly: Boolean = false,
        includeFullText: Boolean = false
    ): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/search/music/byterm") {
                parameter("q", term)
                if (value != Value.Any) {
                    parameter("val", value.encodedValue)
                }
                if (limit > 0) {
                    parameter("max", limit)
                }
                if (aponly) {
                    parameter("aponly", true)
                }
                if (returnCleanOnly) {
                    parameter("clean", true)
                }
                if (includeFullText) {
                    parameter("fulltext", true)
                }
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.SearchForPodcastsSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }
}

enum class Value(val encodedValue: String) {
    Any("any"),
    Lightning("lightning"),
    Hive("hive"),
    WebMonetization("webmonetization")
}
