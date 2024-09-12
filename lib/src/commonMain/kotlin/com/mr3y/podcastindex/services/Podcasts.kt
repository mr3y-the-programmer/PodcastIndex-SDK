package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

class Podcasts internal constructor(private val client: HttpClient) {

    suspend fun byFeedId(id: Int): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/podcasts/byfeedid") {
                parameter("id", id)
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.PodcastSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }

    suspend fun byFeedUrl(url: String): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/podcasts/byfeedurl") {
                parameter("url", url)
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.PodcastSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }

    suspend fun byItunesId(id: Int): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/podcasts/byitunesid") {
                parameter("id", id)
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.PodcastSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }

    suspend fun byGuid(guid: String): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/podcasts/byguid") {
                parameter("guid", guid)
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.PodcastSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }

    suspend fun byMedium(medium: Medium, limit: Int = 0): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/podcasts/bymedium") {
                parameter("medium", medium.value)
                if (limit > 0)
                    parameter("max", limit)
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.PodcastsSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }
}

enum class Medium(val value: String) {
    Podcast("podcast"),
    Music("music"),
    Video("video"),
    Film("film"),
    Audiobook("audiobook"),
    Newsletter("newsletter"),
    Blog("blog"),
    Publisher("publisher"),
    Course("course")
}
