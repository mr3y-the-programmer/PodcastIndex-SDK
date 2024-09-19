package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.extensions.parameterBoolean
import com.mr3y.podcastindex.extensions.parameterLimit
import com.mr3y.podcastindex.extensions.parameterQuery
import com.mr3y.podcastindex.extensions.parameterValue
import com.mr3y.podcastindex.extensions.withErrorHandling
import com.mr3y.podcastindex.model.MultipleEpisodesResult
import com.mr3y.podcastindex.model.MultiplePodcastsResult
import com.mr3y.podcastindex.model.Value
import io.ktor.client.HttpClient
import io.ktor.client.request.get

public class Search internal constructor(private val client: HttpClient) {

    public suspend fun forPodcastsByTerm(
        term: String,
        hasValue: Value = Value.Any,
        limit: Int = 0,
        aponly: Boolean = false,
        returnCleanOnly: Boolean = false,
        includeSimilar: Boolean = false,
        includeFullText: Boolean = false
    ): MultiplePodcastsResult {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/search/byterm") {
                parameterQuery(term)
                parameterValue(hasValue)
                parameterLimit(limit)
                parameterBoolean("aponly", aponly)
                parameterBoolean("clean", returnCleanOnly)
                parameterBoolean("similar", includeSimilar)
                parameterBoolean("fulltext", includeFullText)
            }
        }
    }

    public suspend fun forPodcastsByTitle(
        title: String,
        hasValue: Value = Value.Any,
        limit: Int = 0,
        returnCleanOnly: Boolean = false,
        includeSimilar: Boolean = false,
        includeFullText: Boolean = false
    ): MultiplePodcastsResult {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/search/bytitle") {
                parameterQuery(title)
                parameterValue(hasValue)
                parameterLimit(limit)
                parameterBoolean("clean", returnCleanOnly)
                parameterBoolean("similar", includeSimilar)
                parameterBoolean("fulltext", includeFullText)
            }
        }
    }

    public suspend fun forEpisodesByPerson(
        name: String,
        limit: Int = 0,
        includeFullText: Boolean = false
    ): MultipleEpisodesResult {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/search/byperson") {
                parameterQuery(name)
                parameterLimit(limit)
                parameterBoolean("fulltext", includeFullText)
            }
        }
    }

    public suspend fun forMusicPodcastsByTerm(
        term: String,
        hasValue: Value = Value.Any,
        limit: Int = 0,
        aponly: Boolean = false,
        returnCleanOnly: Boolean = false,
        includeFullText: Boolean = false
    ): MultiplePodcastsResult {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/search/music/byterm") {
                parameterQuery(term)
                parameterValue(hasValue)
                parameterLimit(limit)
                parameterBoolean("aponly", aponly)
                parameterBoolean("clean", returnCleanOnly)
                parameterBoolean("fulltext", includeFullText)
            }
        }
    }
}
