package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.extensions.withErrorHandling
import com.mr3y.podcastindex.model.MultipleEpisodesResult
import com.mr3y.podcastindex.model.ValueFeedResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

public class Value internal constructor(private val client: HttpClient) {

    public suspend fun byFeedId(
        id: Long,
    ): ValueFeedResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/value/byfeedid") {
            parameter("id", id)
        }
    }

    public suspend fun byFeedUrl(
        url: String,
    ): ValueFeedResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/value/byfeedurl") {
            parameter("url", url)
        }
    }

    public suspend fun byFeedGuid(
        guid: String,
    ): ValueFeedResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/value/bypodcastguid") {
            parameter("guid", guid)
        }
    }

    public suspend fun byEpisodeGuid(
        podcastGuid: String,
        episodeGuid: String,
    ): ValueFeedResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/value/byepisodeguid") {
            parameter("podcastguid", podcastGuid)
            parameter("episodeguid", episodeGuid)
        }
    }
}
