package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.extensions.parameterLimit
import com.mr3y.podcastindex.extensions.withErrorHandling
import com.mr3y.podcastindex.model.Medium
import com.mr3y.podcastindex.model.MultiplePodcastsResult
import com.mr3y.podcastindex.model.SinglePodcastResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

public class Podcasts internal constructor(private val client: HttpClient) {

    public suspend fun byFeedId(id: Long): SinglePodcastResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/podcasts/byfeedid") {
            parameter("id", id)
        }
    }

    public suspend fun byFeedUrl(url: String): SinglePodcastResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/podcasts/byfeedurl") {
            parameter("url", url)
        }
    }

    public suspend fun byItunesId(id: Long): SinglePodcastResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/podcasts/byitunesid") {
            parameter("id", id)
        }
    }

    public suspend fun byGuid(guid: String): SinglePodcastResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/podcasts/byguid") {
            parameter("guid", guid)
        }
    }

    public suspend fun byMedium(medium: Medium, limit: Int = 0): MultiplePodcastsResult = withErrorHandling {
        client.get("${PodcastIndexClient.BaseUrl}/podcasts/bymedium") {
            parameter("medium", medium.value)
            parameterLimit(limit)
        }
    }
}
