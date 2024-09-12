package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.model.Category
import com.mr3y.podcastindex.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

typealias LanguageTag = String

const val NoLanguageTag: LanguageTag = "unknown"

class Misc internal constructor(private val client: HttpClient) {

    suspend fun getTrending(
        limit: Int = 0,
        since: Long? = null, // TODO: take a friendly kotlinx.datetime type
        languages: List<LanguageTag> = emptyList(),
        includeCategories: List<Category> = emptyList(),
        excludeCategories: List<Category> = emptyList()
    ): Response {
        return withErrorHandling {
            val response = client.get("${PodcastIndexClient.BaseUrl}/podcasts/trending") {
                if (limit > 0)
                    parameter("max", limit)
                if (since != null) {
                    parameter("since", since)
                }
                if (languages.isNotEmpty()) {
                    parameter("lang", languages.joinToString(separator = ","))
                }
                if (includeCategories.isNotEmpty()) {
                    parameter("cat", includeCategories.joinToString(separator = ",") { it.id.toString() })
                }
                if (excludeCategories.isNotEmpty()) {
                    parameter("notcat", excludeCategories.joinToString(separator = ",") { it.id.toString() })
                }
            }

            when (response.status) {
                HttpStatusCode.OK -> Response.TrendingSuccess(response.body())
                HttpStatusCode.BadRequest -> Response.BadRequest(response.body())
                HttpStatusCode.Unauthorized -> Response.UnAuthenticated("You're Unauthenticated! Make sure to enter your authentication credentials correctly")
                else -> Response.UnknownError(response.bodyAsText())
            }
        }
    }
}
