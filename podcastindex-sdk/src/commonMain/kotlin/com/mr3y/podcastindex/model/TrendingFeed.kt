package com.mr3y.podcastindex.model

import com.mr3y.podcastindex.extensions.CategoriesListSerializer
import com.mr3y.podcastindex.extensions.CategoriesSerializer
import com.mr3y.podcastindex.extensions.InstantSerializer
import dev.drewhamilton.poko.Poko
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Poko
public class TrendingResult(
    @SerialName(value = "feeds") public val feeds: List<TrendingFeed>,
    @SerialName(value = "count") public val count: Int,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class StatsResult(
    @SerialName(value = "stats") public val stats: Stats,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class CategoriesResult(
    @SerialName(value = "feeds") @Serializable(CategoriesListSerializer::class) public val feeds: List<Category>,
    @SerialName(value = "count") public val count: Int,
    @SerialName(value = "description") public val description: String,
)

@Serializable
@Poko
public class Stats(
    @SerialName(value = "feedCountTotal") public val feedCountTotal: Long,
    @SerialName(value = "episodeCountTotal") public val episodeCountTotal: Long,
    @SerialName(value = "feedsWithNewEpisodes3days") public val feedsWithNewEpisodes3days: Long,
    @SerialName(value = "feedsWithNewEpisodes10days") public val feedsWithNewEpisodes10days: Long,
    @SerialName(value = "feedsWithNewEpisodes30days") public val feedsWithNewEpisodes30days: Long,
    @SerialName(value = "feedsWithNewEpisodes90days") public val feedsWithNewEpisodes90days: Long,
    @SerialName(value = "feedsWithValueBlocks") public val feedsWithValueBlocks: Long,
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
    @SerialName(value = "categories") @Serializable(CategoriesSerializer::class) public val categories: List<Category>?,
)
