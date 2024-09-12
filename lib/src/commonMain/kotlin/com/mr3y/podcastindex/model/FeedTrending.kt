package com.mr3y.podcastindex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class FeedTrending(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "url") val url: String,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "description") val description: String? = null,
    @SerialName(value = "author") val author: String? = null,
    @SerialName(value = "image") val image: String? = null,
    @SerialName(value = "artwork") val artwork: String? = null,
    @SerialName(value = "newestItemPublishTime") val newestItemPublishTime: Int? = null,
    @SerialName(value = "itunesId") val itunesId: Int? = null,
    @SerialName(value = "trendScore") val trendScore: Int? = null,
    @SerialName(value = "language") val language: String? = null,
)
