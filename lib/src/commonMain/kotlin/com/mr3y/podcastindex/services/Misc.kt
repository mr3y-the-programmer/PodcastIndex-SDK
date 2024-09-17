package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.extensions.parameterBoolean
import com.mr3y.podcastindex.extensions.parameterLimit
import com.mr3y.podcastindex.extensions.parameterList
import com.mr3y.podcastindex.extensions.withErrorHandling
import com.mr3y.podcastindex.model.Category
import com.mr3y.podcastindex.model.MultipleEpisodesResult
import com.mr3y.podcastindex.model.TrendingResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.Instant

typealias LanguageTag = String

class Misc internal constructor(private val client: HttpClient) {

    suspend fun getTrending(
        limit: Int = 0,
        since: Instant? = null,
        languages: List<LanguageTag> = emptyList(),
        includeCategories: List<Category> = emptyList(),
        excludeCategories: List<Category> = emptyList()
    ): TrendingResult {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/podcasts/trending") {
                parameterLimit(limit)
                parameter("since", since?.toEpochMilliseconds())
                parameterList("lang", languages)
                parameterList("cat", includeCategories, transform = { it.id.toString() })
                parameterList("notcat", excludeCategories, transform = { it.id.toString() })
            }
        }
    }

    suspend fun getLiveEpisodes(
        limit: Int = 0
    ): MultipleEpisodesResult {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/episodes/live") {
                parameterLimit(limit)
            }
        }
    }

    suspend fun getRandomEpisodes(
        limit: Int = 0,
        languages: List<LanguageTag> = emptyList(),
        includeCategories: List<Category> = emptyList(),
        excludeCategories: List<Category> = emptyList(),
        includeFullText: Boolean = false
    ): MultipleEpisodesResult {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/episodes/random") {
                parameterLimit(limit)
                parameterList("lang", languages)
                parameterList("cat", includeCategories, transform = { it.id.toString() })
                parameterList("notcat", excludeCategories, transform = { it.id.toString() })
                parameterBoolean("fulltext", includeFullText)
            }
        }
    }
}
