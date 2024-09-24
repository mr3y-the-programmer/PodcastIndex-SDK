package com.mr3y.podcastindex.model

import com.mr3y.podcastindex.extensions.InstantSerializer
import dev.drewhamilton.poko.Poko
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Poko
public class TrendingResult(
    @SerialName(value = "feeds") public val feeds: List<TrendingFeed>,
)

@Serializable
@Poko
public class TrendingFeed(
    @SerialName(value = "id") public val id: Long,
    @SerialName(value = "url") public val url: String,
    @SerialName(value = "title") public val title: String,
    @SerialName(value = "description") public val description: String,
    @SerialName(value = "author") public val author: String,
    @SerialName(value = "image") public val image: String,
    @SerialName(value = "artwork") public val artwork: String,
    @SerialName(value = "newestItemPublishTime") @Serializable(InstantSerializer::class) public val newestItemPublishTime: Instant,
    @SerialName(value = "itunesId") public val itunesId: Long? = null,
    @SerialName(value = "trendScore") public val trendScore: Int,
    @SerialName(value = "language") public val language: String,
)
