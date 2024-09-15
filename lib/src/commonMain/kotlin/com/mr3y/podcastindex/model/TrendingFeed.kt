package com.mr3y.podcastindex.model

import com.mr3y.podcastindex.extensions.InstantSerializer
import dev.drewhamilton.poko.Poko
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Poko
class TrendingResult(
    @SerialName(value = "feeds") val feeds: List<TrendingFeed>
)

@Serializable
@Poko
class TrendingFeed(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "url") val url: String,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "author") val author: String,
    @SerialName(value = "image") val image: String,
    @SerialName(value = "artwork") val artwork: String,
    @SerialName(value = "newestItemPublishTime") @Serializable(InstantSerializer::class) val newestItemPublishTime: Instant,
    @SerialName(value = "itunesId") val itunesId: Int? = null,
    @SerialName(value = "trendScore") val trendScore: Int,
    @SerialName(value = "language") val language: String,
)
