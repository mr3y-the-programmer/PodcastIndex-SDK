package com.mr3y.podcastindex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface Response {

    @Serializable
    class SearchForPodcastsSuccess(
        @SerialName(value = "feeds") val feeds: List<PodcastFeedSearch>,
    ) : Response

    @Serializable
    class SearchForEpisodesSuccess(
        @SerialName(value = "items") val items: List<EpisodeFeedSearch>,
    ) : Response

    @Serializable
    class PodcastSuccess(
        @SerialName(value = "feed") val feed: PodcastFeedSearch,
    ) : Response

    @Serializable
    class PodcastsSuccess(
        @SerialName(value = "feeds") val feeds: List<PodcastFeedSearch>,
    ) : Response

    @Serializable
    class TrendingSuccess(
        @SerialName(value = "feeds") val feeds: List<FeedTrending>,
    ) : Response

    @Serializable
    class BadRequest(
        @SerialName(value = "description") val description: String
    ) : Response

    @Serializable
    class UnAuthenticated(
        val message: String
    ) : Response

    @Serializable
    class UnknownError(
        val message: String?
    ) : Response
}
