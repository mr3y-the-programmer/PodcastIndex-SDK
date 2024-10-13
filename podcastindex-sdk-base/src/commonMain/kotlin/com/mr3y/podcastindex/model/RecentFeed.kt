package com.mr3y.podcastindex.model

import com.mr3y.podcastindex.extensions.CategoriesSerializer
import com.mr3y.podcastindex.extensions.InstantSerializer
import dev.drewhamilton.poko.Poko
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Poko
public class RecentFeedsResult(
    @SerialName(value = "feeds") public val feeds: List<RecentFeed>,
    @SerialName(value = "count") public val count: Int,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class RecentNewFeedsResult(
    @SerialName(value = "feeds") public val feeds: List<RecentNewFeed>,
    @SerialName(value = "count") public val count: Int,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class SoundbitesResult(
    @SerialName(value = "items") public val items: List<SoundbiteFeed>,
    @SerialName(value = "count") public val count: Int,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class RecentFeed(
    @SerialName(value = "id") public val id: Long,
    @SerialName(value = "url") public val url: String,
    @SerialName(value = "title") public val title: String,
    @SerialName(value = "author") public val author: String? = null,
    @SerialName(value = "image") public val image: String? = null,
    @SerialName(value = "newestItemPublishTime") @Serializable(InstantSerializer::class) public val newestItemPublishTime: Instant,
    @SerialName(value = "oldestItemPublishTime") @Serializable(InstantSerializer::class) public val oldestItemPublishTime: Instant? = null,
    @SerialName(value = "itunesId") public val itunesId: Long? = null,
    @SerialName(value = "trendScore") public val trendScore: Int? = null,
    @SerialName(value = "language") public val language: String,
    @SerialName(value = "categories") @Serializable(CategoriesSerializer::class) public val categories: List<Category>?,
)

@Serializable
@Poko
public class RecentNewFeed(
    @SerialName(value = "id") public val id: Long,
    @SerialName(value = "url") public val url: String,
    @SerialName(value = "image") public val image: String,
    @SerialName(value = "timeAdded") @Serializable(InstantSerializer::class) public val timeAdded: Instant,
    @SerialName(value = "contentHash") public val contentHash: String,
    @SerialName(value = "language") public val language: String,
)

@Serializable
@Poko
public class SoundbiteFeed(
    @SerialName(value = "enclosureUrl") public val enclosureUrl: String,
    @SerialName(value = "title") public val title: String,
    @SerialName(value = "startTime") public val startTime: Int,
    @SerialName(value = "duration") public val duration: Int,
    @SerialName(value = "episodeId") public val episodeId: Long,
    @SerialName(value = "episodeTitle") public val episodeTitle: String,
    @SerialName(value = "feedTitle") public val feedTitle: String,
    @SerialName(value = "feedUrl") public val feedUrl: String,
    @SerialName(value = "feedId") public val feedId: Long,
)
