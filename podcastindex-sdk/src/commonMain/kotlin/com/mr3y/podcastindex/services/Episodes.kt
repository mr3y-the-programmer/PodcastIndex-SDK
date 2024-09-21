package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.extensions.parameterBoolean
import com.mr3y.podcastindex.extensions.parameterLimit
import com.mr3y.podcastindex.extensions.parameterList
import com.mr3y.podcastindex.extensions.withErrorHandling
import com.mr3y.podcastindex.model.MultipleEpisodesResult
import com.mr3y.podcastindex.model.SingleEpisodeResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.datetime.Instant

public class Episodes internal constructor(private val client: HttpClient) {

    public suspend fun byFeedId(
        ids: List<Int>,
        since: Instant? = null,
        limit: Int = 0,
        enclosureUrl: String? = null,
        includeFullText: Boolean = false,
    ): MultipleEpisodesResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/byfeedid") {
            parameterList("id", ids)
            parameter("since", since?.toEpochMilliseconds())
            parameterLimit(limit)
            parameter("enclosure", enclosureUrl)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun byFeedUrl(
        url: String,
        since: Instant? = null,
        limit: Int = 0,
        includeFullText: Boolean = false,
    ): MultipleEpisodesResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/byfeedurl") {
            parameter("url", url)
            parameter("since", since?.toEpochMilliseconds())
            parameterLimit(limit)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun byPodcastGuid(
        guid: String,
        since: Instant? = null,
        limit: Int = 0,
        includeFullText: Boolean = false,
    ): MultipleEpisodesResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/bypodcastguid") {
            parameter("guid", guid)
            parameter("since", since?.toEpochMilliseconds())
            parameterLimit(limit)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun byFeedItunesId(
        id: Int,
        since: Instant? = null,
        limit: Int = 0,
        enclosureUrl: String? = null,
        includeFullText: Boolean = false,
    ): MultipleEpisodesResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/byitunesid") {
            parameter("id", id)
            parameter("since", since?.toEpochMilliseconds())
            parameterLimit(limit)
            parameter("enclosure", enclosureUrl)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun byId(
        id: Long,
        includeFullText: Boolean = false,
    ): SingleEpisodeResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/byid") {
            parameter("id", id)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun byGuidAndFeedId(
        guid: String,
        feedId: Int,
        includeFullText: Boolean = false,
    ): SingleEpisodeResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/byguid") {
            parameter("guid", guid)
            parameter("feedid", feedId)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun byGuidAndFeedUrl(
        guid: String,
        feedUrl: String,
        includeFullText: Boolean = false,
    ): SingleEpisodeResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/byguid") {
            parameter("guid", guid)
            parameter("feedurl", feedUrl)
            parameterBoolean("fulltext", includeFullText)
        }
    }

    public suspend fun byGuidAndFeedGuid(
        guid: String,
        podcastGuid: String,
        includeFullText: Boolean = false,
    ): SingleEpisodeResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/episodes/byguid") {
            parameter("guid", guid)
            parameter("podcastguid", podcastGuid)
            parameterBoolean("fulltext", includeFullText)
        }
    }
}
