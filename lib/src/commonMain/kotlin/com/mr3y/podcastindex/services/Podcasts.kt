package com.mr3y.podcastindex.services

import com.mr3y.podcastindex.PodcastIndexClient
import com.mr3y.podcastindex.extensions.parameterLimit
import com.mr3y.podcastindex.extensions.withErrorHandling
import com.mr3y.podcastindex.model.Medium
import com.mr3y.podcastindex.model.PodcastFeedSearch
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class Podcasts internal constructor(private val client: HttpClient) {

    suspend fun byFeedId(id: Int): PodcastFeedSearch {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/podcasts/byfeedid") {
                parameter("id", id)
            }
        }
    }

    suspend fun byFeedUrl(url: String): PodcastFeedSearch {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/podcasts/byfeedurl") {
                parameter("url", url)
            }
        }
    }

    suspend fun byItunesId(id: Int): PodcastFeedSearch {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/podcasts/byitunesid") {
                parameter("id", id)
            }
        }
    }

    suspend fun byGuid(guid: String): PodcastFeedSearch {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/podcasts/byguid") {
                parameter("guid", guid)
            }
        }
    }

    suspend fun byMedium(medium: Medium, limit: Int = 0): List<PodcastFeedSearch> {
        return withErrorHandling {
            client.get("${PodcastIndexClient.BaseUrl}/podcasts/bymedium") {
                parameter("medium", medium.value)
                parameterLimit(limit)
            }
        }
    }
}
